import java.util.Scanner;

public class Lesson implements Cloneable {
    /* @les_id The id of the lesson
     * @les_name The name of the lesson
     * @_class The class that the lesson can be taught.
     * @weekly_hours How many hours the lesson can be taught weekly.
     */
    private int les_id;
    private String les_name;
    private String _class;
    private int weekly_hours;
    private Scanner sc;
    
    public Lesson(){} //Default constructor. Needed to use the jackson library.
    
    public Lesson(int les_id,String les_name,String _class,int weekly_hours){
        setLes_id(les_id);
        setLes_name(les_name);
        set_class(_class);
        setWeekly_hours(weekly_hours);
    }


    public int getLes_id() {
        return les_id;
    }


    public void setLes_id(int les_id) {
        this.les_id = les_id;
    }


    public String getLes_name() {
        return les_name;
    }


    public void setLes_name(String les_name) {
        this.les_name = les_name;
    }


    public String get_class() {
        return _class;
    }


    public void set_class(String _class) {
    	/*We perform parameters check to ensure
    	 * that our program does not go out of bounds.
    	 * _class = {A,B,C}
    	 */


    	this._class = _class;


    }


    public int getWeekly_hours() {
        return weekly_hours;
    }


    public void setWeekly_hours(int weekly_hours) {
		/*We perform parameters check to ensure
		 * that our program does not go out of bounds.
		 * weekly_hours = {0*}
		 */

        this.weekly_hours = weekly_hours;

    }
    
    @Override
    public boolean equals(Object obj) {
    	if(this.les_name.equals((((Lesson) obj).getLes_name())) && this._class.equals((((Lesson) obj).get_class()))    ) {
    		return true;
    	}
    	return false;
    }
    
    @Override
	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}

	@Override
	public String toString() {
		return getLes_name();
	}

    

}