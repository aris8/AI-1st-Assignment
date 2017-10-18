import java.util.Scanner;

public class Lesson {
    /* @les_id The id of the lesson
     * @les_name The name of the lesson
     * @_class The class that the lesson can be teached.
     * @weekly_hours How many hours the lesson can be teached weekly.
     */
    private int les_id;
    private String les_name;
    private String _class;
    private int weekly_hours;
    private Scanner sc;
    
    public Lesson(){} //Default constructor. Needed to use the jackson library.
    
    public Lesson(int les_id,String les_name,String _class,int weekly_hours,Scanner sc){
        setLes_id(les_id);
        setLes_name(les_name);
        set_class(_class,sc);
        setWeekly_hours(weekly_hours,sc);
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


    public void set_class(String _class,Scanner sc) {
		/*We perform parameters check to ensure
		 * that our program does not go out of bounds.
		 * _class = {A,B,C}
		 */

        while(true){

            if(_class.equals("A") || _class.equals("B") || _class.equals("C")){
                this._class = _class;
                break;
            }
            System.out.printf("Please enter a correct class to assing the Lesson to: ");
            _class = sc.nextLine();
        }

    }


    public int getWeekly_hours() {
        return weekly_hours;
    }


    public void setWeekly_hours(int weekly_hours,Scanner sc) {
		/*We perform parameters check to ensure
		 * that our program does not go out of bounds.
		 * weekly_hours = {0*}
		 */

        while(true){
            if(weekly_hours < 0){
                System.out.printf("The number of weekly hours of class "
                        + "%s are not correct.Please enter an appropriate number of hours: ",get_class());
                weekly_hours = sc.nextInt();
                continue;
            }
            break;
        }
        this.weekly_hours = weekly_hours;

    }


	@Override
	public String toString() {
		return "Lesson [les_id=" + les_id + ", les_name=" + les_name + ", _class=" + _class + ", weekly_hours="
				+ weekly_hours + ", sc=" + sc + "]\n";
	}

    

}