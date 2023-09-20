package pages.actions.json;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Price {

	//CM: Country negocio microsite
	public static String MX_CM = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":0},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"MXN $ ${v}\",\"isPopular\":false,\"currencySymbol\":\"$\",\"currency\":\"MXN\",\"url\":\"https:\\/\\/telmex.com\\/web\\/negocios\\/clarodrive\"},\"300\":{\"smsKey\":\"Drive300\",\"price\":59,\"isPopular\":true},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":169}}";
	public static String CO_CM = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":0,\"url\":\"https:\\/\\/tiendacloud.claro.com.co\\/index.php?redirect=Claro_drive_25G\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$ ${v}\",\"isPopular\":false,\"currencySymbol\":\"$\",\"currency\":\"COP\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":26700,\"url\":\"https:\\/\\/tiendacloud.claro.com.co\\/index.php?redirect=Claro_drive_1TB\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":9600,\"isPopular\":true,\"url\":\"https:\\/\\/tiendacloud.claro.com.co\\/index.php?redirect=Claro_drive_250G\"}}";
	public static String BR_CM = "{\"1536\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1.5T\",\"price\":29.9,\"url\":\"https:\\/\\/loja.embratelcloud.com.br\\/index.php?redirect=CDN15T\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"R$${v} \\/ mês\",\"isPopular\":false,\"currencySymbol\":\"$\",\"currency\":\"R\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":26.9,\"url\":\"https:\\/\\/loja.embratelcloud.com.br\\/index.php?redirect=CDN1T\"},\"30\":{\"smsKey\":\"Drive30\",\"price\":0,\"url\":\"https:\\/\\/loja.embratelcloud.com.br\\/index.php?redirect=CDN30G\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":9.9,\"isPopular\":true,\"url\":\"https:\\/\\/loja.embratelcloud.com.br\\/index.php?redirect=CDN250G\"}}";
	public static String GT_CM = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloud.com.gt\\/index.php\\/?redirect=Claro_drive_25GB\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"Q ${v}\",\"isPopular\":false,\"currencySymbol\":\"Q\",\"currency\":\"GTQ\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":63.5,\"url\":\"https:\\/\\/tienda.clarocloud.com.gt\\/index.php\\/?redirect=Claro_drive_1TB\"},\"350\":{\"smsKey\":\"Drive350\",\"price\":28,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloud.com.gt\\/index.php\\/?redirect=Claro_drive_250GB\"}}";
	public static String HN_CM = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_25GB\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v} *<br>Impuestos no incluidos\",\"isPopular\":false,\"currencySymbol\":\"$\",\"currency\":\"USD\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":7.25,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_1TB\"},\"350\":{\"smsKey\":\"Drive350\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_250GB\"}}";
	public static String NI_CM = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_25GB\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v} *<br>Impuestos no incluidos\",\"isPopular\":false,\"currencySymbol\":\"$\",\"currency\":\"USD\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":7.25,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_1TB\"},\"350\":{\"smsKey\":\"Drive350\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_250GB\"}}";
	public static String SV_CM = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_25GB\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v} *<br>Impuestos no incluidos\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"USD\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":7.25,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_1TB\"},\"350\":{\"smsKey\":\"Drive350\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_250GB\"}}";
	public static String CR_CM = "{\"100\":{\"smskey\":\"Drive100\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_25GB\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v} *<br>Impuestos no incluidos\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"USD\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":7.25,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_1TB\"},\"350\":{\"smsKey\":\"Drive350\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloud-ca.com\\/index.php\\/?redirect=Claro_drive_250GB\"}}";
	public static String PE_CM = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"S\\/. ${v} \\/ MENSUAL<br><span>Incluido IGV<\\/span>\",\"currencySymbol\":\"S\\/.\",\"isPopular\":false,\"currency\":\"PEN\"},\"25\":{\"smskey\":\"Drive25\",\"price\":0,\"url\":\"https:\\/\\/tiendacloud.claro.com.pe\\/index.php?redirect=Claro_drive_25G\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":35,\"url\":\"https:\\/\\/tiendacloud.claro.com.pe\\/index.php?redirect=Claro_drive_1TB\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":12,\"isPopular\":true,\"url\":\"https:\\/\\/tiendacloud.claro.com.pe\\/index.php?redirect=Claro_drive_250G\"}}";
	public static String AR_CM = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$ ${v} ARS\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"ARS\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloudpr.com\\/index.php?redirect=Claro_drive_25G\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":771,\"url\":\"https:\\/\\/tienda.clarocloudpr.com\\/index.php?redirect=Claro_drive_1TB\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":193,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloudpr.com\\/index.php?redirect=Claro_drive_250G\"}}";
	public static String PA_CM = "{\"100\":{\"smskey\":\"Drive100\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloud.com.pa\\/index.php\\/?redirect=Claro_drive_25GB\"},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v} *<br>Impuestos no incluidos\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"USD\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":7.25,\"url\":\"https:\\/\\/tienda.clarocloud.com.pa\\/index.php\\/?redirect=Claro_drive_1TB\"},\"350\":{\"smsKey\":\"Drive350\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloud.com.pa\\/index.php\\/?redirect=Claro_drive_250GB\"}}";
	public static String CL_CM = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"CLP $ ${v}\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"CLP\"},\"25\":{\"smskey\":\"Drive25\",\"price\":0,\"url\":\"https:\\/\\/cp.clarocloud.cl\\/store\\/index.php?redirect=ClaroDrive_25\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":6052,\"url\":\"https:\\/\\/cp.clarocloud.cl\\/store\\/index.php?redirect=ClaroDrive_1024\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":2176,\"isPopular\":true,\"url\":\"https:\\/\\/cp.clarocloud.cl\\/store\\/index.php?redirect=ClaroDrive_250\"}}";
	public static String EC_CM = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v}\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"USD\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"url\":\"https:\\/\\/tiendacloud.claro.com.ec\\/index.php?redirect=Claro_drive_25G\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":8.9,\"url\":\"https:\\/\\/tiendacloud.claro.com.ec\\/index.php?redirect=Claro_drive_1TB\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tiendacloud.claro.com.ec\\/index.php?redirect=Claro_drive_250G\"}}";
	public static String PR_CM = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v} *<br>Impuestos no incluidos\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"USD\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"url\":\"https:\\/\\/tienda.clarocloudpr.com\\/index.php?redirect=Claro_drive_25G\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":7.25,\"url\":\"https:\\/\\/tienda.clarocloudpr.com\\/index.php?redirect=Claro_drive_1TB\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tienda.clarocloudpr.com\\/index.php?redirect=Claro_drive_250G\"}}";
	public static String DO_CM = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"USD $ ${v} *<br>Impuestos no incluidos\",\"currencySymbol\":\"$\",\"isPopular\":false,\"currency\":\"USD\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"url\":\"https:\\/\\/tiendacloud.claro.com.do\\/index.php?redirect=Claro_drive_25G\"},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":7.25,\"url\":\"https:\\/\\/tiendacloud.claro.com.do\\/index.php?redirect=Claro_drive_1TB\"},\"250\":{\"smsKey\":\"Drive250\",\"price\":3.2,\"isPopular\":true,\"url\":\"https:\\/\\/tiendacloud.claro.com.do\\/index.php?redirect=Claro_drive_250G\"}}";
	public static String UY_CM = "";
	public static String PY_CM = "";
	
	//CP: Country providers
	public static String MX_CP = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":0,\"isPopular\":false},\"200\":{\"smsKey\":\"Drive200\",\"price\":19,\"isPopular\":false},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} MXN\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"MXN\",\"type\":\"NONE\"},\"300\":{\"smsKey\":\"Drive300\",\"price\":36,\"isPopular\":true},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":169,\"isPopular\":false}}";
	public static String CO_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} COP\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"COP\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":28000,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":6000,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":3500,\"isPopular\":false}}";
	public static String BR_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"R$${v}\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"R\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"quotaUnity\":\"QUOTA_UNITIES:::tb\",\"smsKey\":\"Drive1T\",\"price\":26.9,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":5.99,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":2.99,\"isPopular\":false}}";
	public static String GT_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"Q${v} GTQ\",\"currencySymbol\":\"Q\",\"planName\":\"NONE\",\"currency\":\"GTQ\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":63.3,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":14.45,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":7.45,\"isPopular\":false}}";
	public static String HN_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"L${v} HNL\",\"currencySymbol\":\"L\",\"planName\":\"NONE\",\"currency\":\"HNL\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":216.9,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":47.2,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":24.5,\"isPopular\":false}}";
	public static String NI_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} USD\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"USD\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":9.2,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":2,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":1.05,\"isPopular\":false}}";
	public static String SV_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} USD\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"USD\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":9.05,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":2,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":1.05,\"isPopular\":false}}";
	public static String CR_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"&#8353;${v} CRC\",\"currencySymbol\":\"&#8353;\",\"planName\":\"NONE\",\"currency\":\"CRC\",\"type\":\"NONE\"},\"25\":{\"smskey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":5150,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":1120,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":580,\"isPopular\":false}}";
	public static String PE_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"S\\/.${v} PEN\",\"currencySymbol\":\"S\\/.\",\"planName\":\"NONE\",\"currency\":\"PEN\",\"type\":\"NONE\"},\"25\":{\"smskey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":20,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":5,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":3,\"isPopular\":false}}";
	public static String AR_CP = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":95,\"isPopular\":true},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} ARS\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"ARS\",\"type\":\"NONE\"},\"25\":{\"smskey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"300\":{\"smsKey\":\"Drive300\",\"price\":280,\"isPopular\":false},\"50\":{\"smsKey\":\"Drive50\",\"price\":50,\"isPopular\":false}}";
	public static String PA_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"B\\/.${v} PAB\",\"currencySymbol\":\"B\\/.\",\"planName\":\"NONE\",\"currency\":\"PAB\",\"type\":\"NONE\"},\"25\":{\"smskey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":8.55,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":1.85,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":0.99,\"isPopular\":false}}";
	public static String CL_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} CLP\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"CLP\",\"type\":\"NONE\"},\"25\":{\"smskey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":6490,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":1490,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":750,\"isPopular\":false}}";
	public static String EC_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} USD\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"USD\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":10.07,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":2.23,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":1.11,\"isPopular\":false}}";
	public static String PR_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$${v} USD\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"USD\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":10.5,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":2,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":1,\"isPopular\":false}}";
	public static String DO_CP = "{\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"RD$${v} DOP\",\"currencySymbol\":\"$\",\"planName\":\"NONE\",\"currency\":\"DOP\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"1024\":{\"smsKey\":\"Drive1T\",\"price\":450,\"isPopular\":false},\"150\":{\"smsKey\":\"Drive150\",\"price\":115,\"isPopular\":true},\"75\":{\"smsKey\":\"Drive75\",\"price\":50,\"isPopular\":false}}";
	public static String UY_CP = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":50,\"isPopular\":true},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"$U${v} UYU\",\"currencySymbol\":\"$U\",\"planName\":\"NONE\",\"currency\":\"DOP\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"300\":{\"smsKey\":\"Drive300\",\"price\":120,\"isPopular\":false},\"50\":{\"smsKey\":\"Drive50\",\"price\":30,\"isPopular\":false}}";
	public static String PY_CP = "{\"100\":{\"smsKey\":\"Drive100\",\"price\":5000,\"isPopular\":true},\"default\":{\"quotaUnity\":\"QUOTA_UNITIES:::gb\",\"compoundPrice\":\"Gs ${v}\",\"currencySymbol\":\"Gs\",\"planName\":\"NONE\",\"currency\":\"PYG\",\"type\":\"NONE\"},\"25\":{\"smsKey\":\"Drive25\",\"price\":0,\"isPopular\":false},\"300\":{\"smsKey\":\"Drive300\",\"price\":10000,\"isPopular\":false},\"50\":{\"smsKey\":\"Drive50\",\"price\":3000,\"isPopular\":false}}";
	
}
