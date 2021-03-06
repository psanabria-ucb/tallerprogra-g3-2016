package bo.edu.ucbcba.hotel.model;

import bo.edu.ucbcba.hotel.exceptions.ValidationException;

/**
 * Created by CésarIvan on 26/06/2016.
 */

public class Dates {
    private int day;
    private int month;
    private int year;

    String date;

    public Dates(){
        //initial
        this.day=01;
        this.month=01;
        this.year=1988;
        this.date= this.formatDate();
    }

    public Dates(int day, int month, int year)
    {
        this.setDate(day,month,year);
    }

    public int getDay()
    {
        return this.day;
    }

    public int getMonth()
    {
        return this.month;
    }

    public int getYear()
    {
        return this.year;
    }

    public void setDate(int day,int month,int year)
    {
        this.year=year;
        if(isAvalidDate(day, month)){
        	this.day = day;
            this.month = month;
        }
    }

	private boolean isAvalidDate(int day, int month) {
		boolean h=false;
		if(isAvalidMonth(month))
        {
            throw new ValidationException("Error format Month");
        }
        else
        {
            h = isAvalidDay(day, month);
        }
		return h;
	}

	private boolean isAvalidDay(int day, int month) {
		boolean h=false;
		if(day > 31 && day<1)
		{
		    throw new ValidationException("Error format Day");
		}
		else
		{
		    if((month == 4 || month == 6 || month == 9 || month == 11)&&(day > 30))
		    {
		        throw new ValidationException("Error This month have 30 days");
		    }
		    else
		    {
		        if(month == 2 && bisiest() && day > 29)
		        {
		            throw new ValidationException("Error This month have 29 days");
		        }
		        else if (month == 2 && !bisiest() && day > 28)
		        {
		            throw new ValidationException("Error This month have 28 days");
		        }
		        else
		        {
		        	h=true;
		            
		        }
		    }
		}
		return h;
	}

	private boolean isAvalidMonth(int month) {
		return month >12 && month <1;
	}

    public String formatDate()
    {
        return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
    }

    public void printDate()
    {
        System.out.println(this.formatDate());
    }

    public boolean bisiest() {
        if (this.getYear() % 400 == 0) {
            return true;
        } else {
            if (this.getYear() % 4 == 0 && this.getYear() % 100 != 0) {
                return true;
            } else {
                return false;
            }
        }
    }



}
