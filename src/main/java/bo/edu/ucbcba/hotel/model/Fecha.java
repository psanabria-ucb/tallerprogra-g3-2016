package bo.edu.ucbcba.hotel.model;

import bo.edu.ucbcba.hotel.exceptions.ValidationException;

/**
 * Created by CésarIvan on 02/06/2016.
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha()
    {
// initialise instance variables
        this.dia = 01;
        this.mes = 01;
        this.anio = 1980;
    }

    public Fecha(int dia, int mes, int anio)
    {
        this.setFecha(dia,mes,anio);
    }

    public int getDia()
    {
        return this.dia;
    }

    public int getMes()
    {
        return this.mes;
    }

    public int getAnio()
    {
        return this.anio;
    }

    public void setFecha(int dia,int mes,int anio)
    {
        this.anio=anio;
        if(mes >12 && mes <1)
        {
            throw new ValidationException("Error format Month");
        }
        else
        {
            if(dia > 31 && dia<1)
            {
                throw new ValidationException("Error format Day");
            }
            else
            {
                if((mes == 4 || mes == 6 || mes == 9 || mes == 11)&&(dia > 30))
                {
                    throw new ValidationException("Error This month have 30 days");
                }
                else
                {
                    if(mes == 2 && bisiesto() && dia > 29)
                    {
                        throw new ValidationException("Error This month have 29 days");
                    }
                    else if (mes == 2 && !bisiesto() && dia > 28)
                    {
                        throw new ValidationException("Error This month have 28 days");
                    }
                    else
                    {
                        this.dia = dia;
                        this.mes = mes;
                    }
                }
            }
        }
    }

    public String formatoFecha()
    {
        return this.getDia() + "/" + this.getMes() + "/" + this.getAnio();
    }

    public void imprimirFecha()
    {
        System.out.println(this.formatoFecha());
    }

    public boolean bisiesto() {
        if (this.getAnio() % 400 == 0) {
            return true;
        } else {
            if (this.getAnio() % 4 == 0 && this.getAnio() % 100 != 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
