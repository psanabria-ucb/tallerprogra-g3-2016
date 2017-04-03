package bo.edu.ucbcba.hotel.model;

import bo.edu.ucbcba.hotel.exceptions.ValidationException;

import javax.persistence.*;

/**
 * Created by CésarIvan on 27/06/2016.
 */
@Entity
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reportId; // Primary Key, and Auto Generated

    private int day;
    private int month;
    private int anio;

    @Column(length = 500)
    private String text;
    @Column(length = 100)
    private String type;

    private String date;



    public Reports(){
        text="";
        type="";
        day=01;
        month=01;
        anio=1980;
        date=formatDate();
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return this.anio;
    }

    public void setDate(int day,int month,int year)
    {
        this.anio=year;
        if(isValidDate(day, month)){
        	this.day = day;
            this.month = month;
            this.date=formatDate();
        }
    }

	private boolean isValidDate(int day, int month) {
		boolean h=false;
		if(isAvalidMonth(month))
        {
            throw new ValidationException("Error format Month");
        }
        else
        {
            h = isValidTheDay(day, month);
        }
		return h;
	}

	private boolean isValidTheDay(int day, int month) {
		boolean h=false;
		if(isAvalidDay(day))
		{
		    throw new ValidationException("Error format Day");
		}
		else
		{
		    if(areTheDaysAccordingToTheMonth(day, month))
		    {
		        throw new ValidationException("Error This month have 30 days");
		    }
		    else
		    {
		        if(isTheDaysAccordingToTheMonthInBisiestYear(day, month))
		        {
		            throw new ValidationException("Error This month have 29 days");
		        }
		        else if (isTheDaysAccordingToTheMonthInNormalYear(day, month))
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

	private boolean isTheDaysAccordingToTheMonthInNormalYear(int day, int month) {
		return month == 2 && !bisiest() && day > 28;
	}

	private boolean isTheDaysAccordingToTheMonthInBisiestYear(int day, int month) {
		return month == 2 && bisiest() && day > 29;
	}

	private boolean areTheDaysAccordingToTheMonth(int day, int month) {
		return (month == 4 || month == 6 || month == 9 || month == 11)&&(day > 30);
	}

	private boolean isAvalidDay(int day) {
		return day > 31 && day<1;
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

    public String getDate() {
        return date;
    }
}
