package com.proyectoFinal.proyectoFinal.Services;

import com.proyectoFinal.proyectoFinal.model.ExcelCabecera;
import com.proyectoFinal.proyectoFinal.model.ExcelDetalle;
import com.proyectoFinal.proyectoFinal.model.ExcelValores;
import com.proyectoFinal.proyectoFinal.model.Usuario;
import com.proyectoFinal.proyectoFinal.repository.CabeceraRepository;
import com.proyectoFinal.proyectoFinal.repository.DetalleRepository;
import com.proyectoFinal.proyectoFinal.repository.ValoresRepository;
import com.proyectoFinal.proyectoFinal.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileServices {
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	DetalleRepository detalleRepository;
	@Autowired
	ValoresRepository valoresRepository;

	@Autowired
	CabeceraRepository cabeceraRepository;
	
	// Store File Data to Database
	public String store(MultipartFile file){
		String result = "false";
		try {
			List<Object> lstCustomers = ExcelUtils.detalle(file.getInputStream());
			List<ExcelCabecera> cabecera =(List)lstCustomers.get(2);


			if (getCabeceras().size() != 0) {
				for (int i = 0; i < getCabeceras().size(); i++) {
					if (getCabeceras().get(i).getReferencia().equals(cabecera.get(0).getReferencia())
					) {
						result = "true";
						break;
					}
				}
			}

			if(result=="false"){
				List<ExcelDetalle> lstCustomerss =(List)lstCustomers.get(0);
				detalleRepository.saveAll(lstCustomerss);
				List<ExcelValores> valores =(List)lstCustomers.get(1);
				valoresRepository.saveAll(valores);
				cabeceraRepository.saveAll(cabecera);
			}

        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }

		return result;
	}


	@Transactional
	public List<ExcelCabecera> getCabeceras() {
		String query = "FROM ExcelCabecera";

		return entityManager.createQuery(query).getResultList();

	}
	
	// Load Data to Excel File
//    public ByteArrayInputStream loadFile() {
//    	List<Customer> customers = (List<Customer>) customerRepository.findAll();
//
//    	try {
//    		ByteArrayInputStream in = ExcelUtilsCabecera.customersToExcel(customers);
//    		return in;
//		} catch (IOException e) {}
//
//        return null;
//    }
}
