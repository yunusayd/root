package imageArranger;

public class MonthNames {
	public static final String Jan = "Ocak";
	public static final String Feb = "Subat";
	public static final String Mar = "Mart";
	public static final String Apr = "Nisan";
	public static final String May = "Mayis";
	public static final String Jun = "Haziran";
	public static final String Jul = "Temmuz";
	public static final String Aug = "Agustos";
	public static final String Sep = "Eylul";
	public static final String Oct = "Ekim";
	public static final String Nov = "Kasim";
	public static final String Dec = "Aralik";

	public static Object getMonthName(int month) {
		switch (month) {

		case 0:
			return Jan;
		case 1:
			return Feb;
		case 2:
			return Mar;
		case 3:
			return Apr;
		case 4:
			return May;
		case 5:
			return Jun;
		case 6:
			return Jul;
		case 7:
			return Aug;
		case 8:
			return Sep;
		case 9:
			return Oct;
		case 10:
			return Nov;
		case 11:
			return Dec;
		default:
			return "";
		}
	}
	
}
