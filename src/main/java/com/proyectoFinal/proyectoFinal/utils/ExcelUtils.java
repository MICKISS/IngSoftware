package com.proyectoFinal.proyectoFinal.utils;


import com.proyectoFinal.proyectoFinal.model.ExcelCabecera;
import com.proyectoFinal.proyectoFinal.model.ExcelDetalle;
import com.proyectoFinal.proyectoFinal.model.ExcelValores;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {


//    public static ByteArrayInputStream customersToExcel(List<Customer> customers) throws IOException {
//        String[] COLUMNs = {"Id", "Name", "Address", "Age"};
//        try (
//                Workbook workbook = new XSSFWorkbook();
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ) {
//            CreationHelper createHelper = workbook.getCreationHelper();
//
//            Sheet sheet = workbook.createSheet("Customers");
//
//            Font headerFont = workbook.createFont();
//            headerFont.setBold(true);
//            headerFont.setColor(IndexedColors.BLUE.getIndex());
//
//            CellStyle headerCellStyle = workbook.createCellStyle();
//            headerCellStyle.setFont(headerFont);
//
//            // Row for Header
//            Row headerRow = sheet.createRow(0);
//
//            // Header
//            for (int col = 0; col < COLUMNs.length; col++) {
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(COLUMNs[col]);
//                cell.setCellStyle(headerCellStyle);
//            }
//
//            // CellStyle for Age
//            CellStyle ageCellStyle = workbook.createCellStyle();
//            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
//
//            int rowIdx = 1;
//            for (Customer customer : customers) {
//                Row row = sheet.createRow(rowIdx++);
//
//                row.createCell(0).setCellValue(customer.getId());
//                row.createCell(1).setCellValue(customer.getName());
//                row.createCell(2).setCellValue(customer.getAddress());
//
//                Cell ageCell = row.createCell(3);
//                ageCell.setCellValue(customer.getAge());
//                ageCell.setCellStyle(ageCellStyle);
//            }
//
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        }
//    }


    public static List<Object> detalle(InputStream is) {
        ArrayList<Object> listaTablas = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet("Hoja1");
            Iterator<Row> rows = sheet.iterator();

            List<ExcelDetalle> lstCustomers = new ArrayList<ExcelDetalle>();
            List<ExcelValores> lstValores = new ArrayList<ExcelValores>();
            List<ExcelCabecera> lstCabecera = new ArrayList<ExcelCabecera>();
            String referencia = "";

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                int rowNum = currentRow.getRowNum();

                if (rowNum == 3 || rowNum > 6) {
                    Iterator<Cell> cellsInRow = currentRow.iterator();

                    ExcelDetalle detalle = new ExcelDetalle();
                    ExcelValores valores = new ExcelValores();
                    ExcelCabecera cabecera = new ExcelCabecera();


                    int cellIndex = 0;

                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();

                        if (rowNum == 3) {
                            String valCell = currentCell.getStringCellValue();

                            if (cellIndex == 0) {
                                cabecera.setTipoDocumento(valCell);
                            } else if (cellIndex == 1) {
                                cabecera.setNoDocumento(valCell);
                            } else if (cellIndex == 2) {
                                cabecera.setRazonSocial(valCell);
                            } else if (cellIndex == 3) {
                                referencia = valCell;
                                cabecera.setReferencia(valCell);
                            } else if (cellIndex == 4) {
                                cabecera.setSolicitud(valCell);
                            }


                        } else if (rowNum > 6) {
                            if (cellIndex == 0) {

                                detalle.setOrden((int) currentCell.getNumericCellValue());
                            } else {
                                String valCell = "";
                                double valCellNum = 0;

                                if (currentCell.getCellType() == 0) {
                                    if (cellIndex < 13) {
                                        valCell = String.valueOf(currentCell.getNumericCellValue());

                                    } else {
                                        valCellNum = currentCell.getNumericCellValue();

                                    }
                                } else {
                                    valCell = currentCell.getStringCellValue();

                                }


                                if (cellIndex == 1) {
                                    detalle.setTipoDocumento(valCell);
                                } else if (cellIndex == 2) {
                                    detalle.setNoDocumento(valCell);
                                } else if (cellIndex == 3) {
                                    detalle.setCotizante(valCell);
                                } else if (cellIndex == 4) {
                                    detalle.setCargo(valCell);
                                } else if (cellIndex == 5) {
                                    detalle.setAnio(valCell);
                                } else if (cellIndex == 6) {
                                    detalle.setMes(valCell);
                                } else if (cellIndex == 7) {
                                    detalle.setSalario(valCell);
                                } else if (cellIndex == 8) {
                                    detalle.setDiasTrabajados(valCell);
                                } else if (cellIndex == 9) {
                                    detalle.setDiasIncap(valCell);
                                } else if (cellIndex == 10) {
                                    detalle.setDiasLicen(valCell);
                                } else if (cellIndex == 11) {
                                    detalle.setTotalDias(valCell);
                                } else if (cellIndex == 12) {
                                    detalle.setFechaIngreso(valCell);
                                }
                                detalle.setReferencia(referencia);


                                if (cellIndex == 13) {
                                    valores.setSueldoBasico(valCellNum);
                                } else if (cellIndex == 14) {
                                    valores.setApoyoSoli(valCellNum);
                                } else if (cellIndex == 15) {
                                    valores.setHoraExtraDiurno(valCellNum);
                                } else if (cellIndex == 16) {
                                    valores.setHoraExtraFa(valCellNum);
                                } else if (cellIndex == 17) {
                                    valores.setComisiones(valCellNum);
                                } else if (cellIndex == 18) {
                                    valores.setVacacionesD(valCellNum);
                                } else if (cellIndex == 19) {
                                    valores.setVacacionesO(valCellNum);
                                } else if (cellIndex == 20) {
                                    valores.setAj(valCellNum);
                                } else if (cellIndex == 21) {
                                    valores.setBonoRetiro(valCellNum);
                                } else if (cellIndex == 22) {
                                    valores.setCompensacion(valCellNum);
                                } else if (cellIndex == 23) {
                                    valores.setIncapacidades(valCellNum);

                                }
                                valores.setReferencia(referencia);
                            }
                        }
                        cellIndex++;
                    }
                    if (rowNum > 6) {
                        lstCustomers.add(detalle);
                        lstValores.add(valores);

                    } else if (rowNum == 3) {
                        lstCabecera.add(cabecera);

                    }


                }

            }

            // Close WorkBook
            workbook.close();
            listaTablas.add(lstCustomers);
            listaTablas.add(lstValores);
            listaTablas.add(lstCabecera);
            return listaTablas;
        } catch (
                IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }
}