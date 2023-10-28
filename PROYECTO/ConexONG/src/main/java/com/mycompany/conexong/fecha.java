/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.conexong;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author VALENTINA
 */
public class fecha {
         Calendar f = new GregorianCalendar();
     
        String anio = Integer.toString(f.get(Calendar.YEAR));
        String mes = Integer.toString(f.get(Calendar.MONTH));
        String dia = Integer.toString(f.get(Calendar.DATE));
        
        String fechactual = dia + "/"+ mes + "/" +anio;
        
        String hora = Integer.toString(f.get(Calendar.HOUR_OF_DAY));
        String min = Integer.toString(f.get(Calendar.MINUTE));
        String seg = Integer.toString(f.get(Calendar.SECOND));
        
        String horaact = hora + ":"+ min ;
}
