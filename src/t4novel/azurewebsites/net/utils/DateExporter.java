package t4novel.azurewebsites.net.utils;

import java.time.LocalDate;

public class DateExporter {
	/**
	 * @author Maf
	 * @param src the src param with the accepted format is  YYYY-MM-dd
	 * @return return a instance of LocalDate related with src param
	 * 
	 * 
	 * */
		public static LocalDate extractDate(String src) {
			return LocalDate.of(Integer.parseInt(src.substring(0, 4)), Integer.parseInt(src.substring(5, 7)),
					Integer.parseInt(src.substring(8, 10)));
		}
}
