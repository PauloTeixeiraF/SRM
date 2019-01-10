package com.srm.test.api.v1;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.srm.test.domain.orm.CreditLimit;
import com.srm.test.service.CreditLimitService;
import com.srm.test.util.CustomErrorType;



@RestController
@RequestMapping("/api/v1")
public class CreditLimitRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(CreditLimitRestController.class); 
	
	private UriComponentsBuilder ucBuilder;
	
	@Autowired
	private CreditLimitService creditLimitService;
	
	
	// -------------------Create a Credit Limit-------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> createCreditlimit(@RequestBody CreditLimit entity){
		
		logger.info("Creating Credit Limit : {}", entity);
		
		if (creditLimitService.isCreditLimitExist(entity)) {
            logger.error("Unable to create. A CreditLimit with name {} already exist", entity.getName());
            return new ResponseEntity(new CustomErrorType("Impossível salvar! Uma avaliação de limite de crédito com o nome " + 
            entity.getName() + " já existe."),HttpStatus.CONFLICT);
        }
		
		HttpHeaders headers = new HttpHeaders();
        if(entity == null) {
            return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
        }
        CreditLimit item = creditLimitService.findByName(entity.getName());
        if(item == null) {
        	creditLimitService.createCreditLimit(entity);
        } else {
        	headers.setLocation(ucBuilder.path("/api/v1/{id}").buildAndExpand(item.getId()).toUri());
        }
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	// -------------------Retrieve All Credit Limits---------------------------------------------
    
	@RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listAll() {
        List<CreditLimit> itens = creditLimitService.findAllCreditLimits();
        if (itens.isEmpty()) {
            return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
            // pode se usar também return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<?>>(itens, HttpStatus.OK);
    }
 
	
    // -------------------Retrieve Single Credit Limit------------------------------------------
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCreditLimit(@PathVariable("id") long id) {
        logger.info("Fetching CreditLimit with id {}", id);
        CreditLimit creditLimit = creditLimitService.findById(id);
        if (creditLimit == null) {
            logger.error("CreditLimit with id {} not found.", id);
            return new ResponseEntity<Object>(new CustomErrorType("Limite de Crédito com id " + id 
                    + " não encontrado!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CreditLimit>(creditLimit, HttpStatus.OK);
    }

	
   // ------------------- Update a Credit Limit ------------------------------------------------
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCreditLimit(@PathVariable("id") long id, @RequestBody CreditLimit entity) {
        logger.info("Updating User with id {}", id);
 
        CreditLimit currentItem = creditLimitService.findById(id);
 
        if (currentItem == null) {
            logger.error("Unable to update. CreditLimit with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Impossível Atualizar! A avaliação de limite de crédito com id " + id
            		+ " não foi encontrada."), HttpStatus.NOT_FOUND);
        }
 
        currentItem.setName(entity.getName());
        currentItem.setCreditLimit(entity.getCreditLimit());
        currentItem.setRisk(entity.getRisk());
 
        creditLimitService.updateCreditLimit(currentItem);
        return new ResponseEntity<CreditLimit>(currentItem, HttpStatus.OK);
    }
 
    
    // ------------------- Delete a Credit Limit-----------------------------------------
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCreditLimit(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting CreditLimit with id {}", id);
 
        CreditLimit item = creditLimitService.findById(id);
        if (item == null) {
            logger.error("Unable to delete. CreditLimit with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Impossível excluir! A avaliação de crédito com id " + id + " não foi encontrada."),
                    HttpStatus.NOT_FOUND);
        }
        creditLimitService.deleteCreditLimitById(id);
        return new ResponseEntity<CreditLimit>(HttpStatus.NO_CONTENT);
    }
    
 
    // ------------------- Delete All Credit Limits-----------------------------
 
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<CreditLimit> deleteAllCreditLimits() {
        logger.info("Deleting All CreditLimits");
 
        creditLimitService.deleteAllCreditLimits();
        return new ResponseEntity<CreditLimit>(HttpStatus.NO_CONTENT);
    }
 
}
