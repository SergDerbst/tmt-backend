package com.toomanythoughts.tmt.web.layers.logic.geo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.toomanythoughts.tmt.web.layers.logic.geo.model.CountryMetaData;
import com.toomanythoughts.tmt.web.layers.logic.geo.model.CountryNameEntry;

@Component
public class CountryMetaDataService {

	private final List<CountryMetaData> countries = new ArrayList<>();

	public CountryMetaDataService() {
		this.buildMetaData();
	}

	public Set<CountryNameEntry> fetchCountryNames(final String typed, final Locale lang) {
		final Set<CountryNameEntry> matches = new TreeSet<>();
		if (!typed.isBlank()) {
			for (final CountryMetaData country : this.countries) {
				final String countryNames = country.getCountryNames().get(LanguageCode.getByLocale(lang));
				if (countryNames.contains(typed)) {
					for (final String name : countryNames.split("\\|")) {
						if (name.contains(typed)) {
							matches.add(new CountryNameEntry(country.getCountryCode(), name));
						}
					}
				}
			}
		}
		return matches;
	}

	private void buildMetaData() {
		//source: https://www.iban.com/country-codes
		this.countries.add(new CountryMetaData(CountryCode.AF, LanguageCode.en + "|Afghanistan", LanguageCode.de + "|Afghanistan"));
		this.countries.add(new CountryMetaData(CountryCode.AL, LanguageCode.en + "|Albania", LanguageCode.de + "|Albanien"));
		this.countries.add(new CountryMetaData(CountryCode.DZ, LanguageCode.en + "|Algeria", LanguageCode.de + "|Algerien"));
		this.countries.add(new CountryMetaData(CountryCode.AS, LanguageCode.en + "|American Samoa", LanguageCode.de + "|Amerikanisch Samoa"));
		this.countries.add(new CountryMetaData(CountryCode.AD, LanguageCode.en + "|Andorra", LanguageCode.de + "|Andorra"));
		this.countries.add(new CountryMetaData(CountryCode.AO, LanguageCode.en + "|Angola", LanguageCode.de + "|Angola"));
		this.countries.add(new CountryMetaData(CountryCode.AI, LanguageCode.en + "|Anguilla", LanguageCode.de + "|Anguilla"));
		this.countries.add(new CountryMetaData(CountryCode.AQ, LanguageCode.en + "|Antarctica", LanguageCode.de + "|Antarktis"));
		this.countries.add(new CountryMetaData(CountryCode.AG, LanguageCode.en + "|Antigua and Barbuda", LanguageCode.de + "|Antigua und Barbuda"));
		this.countries.add(new CountryMetaData(CountryCode.AR, LanguageCode.en + "|Argentinia", LanguageCode.de + "|Argentinien"));
		this.countries.add(new CountryMetaData(CountryCode.AM, LanguageCode.en + "|Armenia", LanguageCode.de + "|Armenien"));
		this.countries.add(new CountryMetaData(CountryCode.AW, LanguageCode.en + "|Aruba", LanguageCode.de + "|Aruba"));
		this.countries.add(new CountryMetaData(CountryCode.AW, LanguageCode.en + "|Australia", LanguageCode.de + "|Australien"));
		this.countries.add(new CountryMetaData(CountryCode.AW, LanguageCode.en + "|Austria", LanguageCode.de + "|Österreich"));
		this.countries.add(new CountryMetaData(CountryCode.AW, LanguageCode.en + "|Azerbaijan", LanguageCode.de + "|Aserbaidschan"));

		this.countries.add(new CountryMetaData(CountryCode.BS, LanguageCode.en + "|Bahamas", LanguageCode.de + "|Bahamas"));
		this.countries.add(new CountryMetaData(CountryCode.BH, LanguageCode.en + "|Bahrain", LanguageCode.de + "|Bahrain"));
		this.countries.add(new CountryMetaData(CountryCode.BD, LanguageCode.en + "|Bangladesh", LanguageCode.de + "|Bangladesch"));
		this.countries.add(new CountryMetaData(CountryCode.BB, LanguageCode.en + "|Barbados", LanguageCode.de + "|Barbados"));
		this.countries.add(new CountryMetaData(CountryCode.BY, LanguageCode.en + "|Belarus", LanguageCode.de + "|Belarus"));
		this.countries.add(new CountryMetaData(CountryCode.BE, LanguageCode.en + "|Belgium", LanguageCode.de + "|Belgien"));
		this.countries.add(new CountryMetaData(CountryCode.BZ, LanguageCode.en + "|Belize", LanguageCode.de + "|Belize"));
		this.countries.add(new CountryMetaData(CountryCode.BJ, LanguageCode.en + "|Benin", LanguageCode.de + "|Benin"));
		this.countries.add(new CountryMetaData(CountryCode.BM, LanguageCode.en + "|Bermuda", LanguageCode.de + "|Bermuda"));
		this.countries.add(new CountryMetaData(CountryCode.BT, LanguageCode.en + "|Bhutan", LanguageCode.de + "|Bhutan"));
		this.countries.add(new CountryMetaData(CountryCode.BO, LanguageCode.en + "|Bolovia", LanguageCode.de + "|Bolivien"));
		this.countries.add(new CountryMetaData(CountryCode.BQ, LanguageCode.en + "|Carribean Netherlands", LanguageCode.de + "|Karibisch Niederlande"));
		this.countries.add(new CountryMetaData(CountryCode.BA, LanguageCode.en + "|Bosnia and Herzegovina", LanguageCode.de + "|Bosnien-Herzigowina"));
		this.countries.add(new CountryMetaData(CountryCode.BW, LanguageCode.en + "|Botswana", LanguageCode.de + "|Botswana"));
		this.countries.add(new CountryMetaData(CountryCode.BV, LanguageCode.en + "|Bouvet Island", LanguageCode.de + "|Bouvetinsel"));
		this.countries.add(new CountryMetaData(CountryCode.BR, LanguageCode.en + "|Brazil", LanguageCode.de + "|Brasilien"));
		this.countries.add(new CountryMetaData(CountryCode.IO, LanguageCode.en + "|British Indian Ocean Territory", LanguageCode.de + "|Britisches Territorium im Indischen Ozean"));
		this.countries.add(new CountryMetaData(CountryCode.BN, LanguageCode.en + "|Brunei Darussalam", LanguageCode.de + "|Brunei Darussalam"));
		this.countries.add(new CountryMetaData(CountryCode.BG, LanguageCode.en + "|Bulgaria", LanguageCode.de + "|Bulgarien"));
		this.countries.add(new CountryMetaData(CountryCode.BF, LanguageCode.en + "|Burkina Faso", LanguageCode.de + "|Burkina Faso"));
		this.countries.add(new CountryMetaData(CountryCode.BI, LanguageCode.en + "|Burundi", LanguageCode.de + "|Burundi"));

		this.countries.add(new CountryMetaData(CountryCode.CV, LanguageCode.en + "|Cabo Verde", LanguageCode.de + "|Kap Verde"));
		this.countries.add(new CountryMetaData(CountryCode.KH, LanguageCode.en + "|Cambodia", LanguageCode.de + "|Kambodscha"));
		this.countries.add(new CountryMetaData(CountryCode.CM, LanguageCode.en + "|Cameroon", LanguageCode.de + "|Kamerun"));
		this.countries.add(new CountryMetaData(CountryCode.CA, LanguageCode.en + "|Canada", LanguageCode.de + "|Kanada"));
		this.countries.add(new CountryMetaData(CountryCode.KY, LanguageCode.en + "|Cayman Islands", LanguageCode.de + "|Caymaninseln"));
		this.countries.add(new CountryMetaData(CountryCode.CF, LanguageCode.en + "|Central Afrikan Republic", LanguageCode.de + "|Zentralfrikanische Republik"));
		this.countries.add(new CountryMetaData(CountryCode.TD, LanguageCode.en + "|Chad", LanguageCode.de + "|Tschad"));
		this.countries.add(new CountryMetaData(CountryCode.CL, LanguageCode.en + "|Chile", LanguageCode.de + "|Chile"));
		this.countries.add(new CountryMetaData(CountryCode.CN, LanguageCode.en + "|China", LanguageCode.de + "|China"));
		this.countries.add(new CountryMetaData(CountryCode.CX, LanguageCode.en + "|Cristmas Island", LanguageCode.de + "|Weihnachtsinsel"));
		this.countries.add(new CountryMetaData(CountryCode.CC, LanguageCode.en + "|Cocos Islands", LanguageCode.de + "|Kokosinseln"));
		this.countries.add(new CountryMetaData(CountryCode.CO, LanguageCode.en + "|Colombia", LanguageCode.de + "|Kolumbien"));
		this.countries.add(new CountryMetaData(CountryCode.KM, LanguageCode.en + "|Comoros", LanguageCode.de + "|Komoren"));
		this.countries.add(new CountryMetaData(CountryCode.CD, LanguageCode.en + "|Democratic Republic of Congo", LanguageCode.de + "|Demokratische Republik Kongo"));
		this.countries.add(new CountryMetaData(CountryCode.CG, LanguageCode.en + "|Congo", LanguageCode.de + "|Kongo"));
		this.countries.add(new CountryMetaData(CountryCode.CK, LanguageCode.en + "|Cook Islands", LanguageCode.de + "|Cookinseln"));
		this.countries.add(new CountryMetaData(CountryCode.CR, LanguageCode.en + "|Costa Rica", LanguageCode.de + "|Costa Rica"));
		this.countries.add(new CountryMetaData(CountryCode.HR, LanguageCode.en + "|Croatia", LanguageCode.de + "|Kroatien"));
		this.countries.add(new CountryMetaData(CountryCode.CU, LanguageCode.en + "|Cuba", LanguageCode.de + "|Kuba"));
		this.countries.add(new CountryMetaData(CountryCode.CW, LanguageCode.en + "|Curaçao", LanguageCode.de + "|Curaçao"));
		this.countries.add(new CountryMetaData(CountryCode.CY, LanguageCode.en + "|Cyprus", LanguageCode.de + "|Zypern"));
		this.countries.add(new CountryMetaData(CountryCode.CZ, LanguageCode.en + "|Czechia", LanguageCode.de + "|Tschechien"));
		this.countries.add(new CountryMetaData(CountryCode.CI, LanguageCode.en + "|Côte d'Ivoire", LanguageCode.de + "|Elfenbeinküste"));

		this.countries.add(new CountryMetaData(CountryCode.DK, LanguageCode.en + "|Denmark", LanguageCode.de + "|Dänemark"));
		this.countries.add(new CountryMetaData(CountryCode.DJ, LanguageCode.en + "|Djibouti", LanguageCode.de + "|Dschibuti"));
		this.countries.add(new CountryMetaData(CountryCode.DM, LanguageCode.en + "|Dominica", LanguageCode.de + "|Dominica"));
		this.countries.add(new CountryMetaData(CountryCode.DO, LanguageCode.en + "|Dominican Republic", LanguageCode.de + "|Dominikanische Republik"));

		this.countries.add(new CountryMetaData(CountryCode.EC, LanguageCode.en + "|Ecuador", LanguageCode.de + "|Ecuador"));
		this.countries.add(new CountryMetaData(CountryCode.EG, LanguageCode.en + "|Egypt", LanguageCode.de + "|Ägypten"));
		this.countries.add(new CountryMetaData(CountryCode.SV, LanguageCode.en + "|El Salvador", LanguageCode.de + "|El Salvador"));
		this.countries.add(new CountryMetaData(CountryCode.GQ, LanguageCode.en + "|Equatorial Guinea", LanguageCode.de + "|Äquatorianlguinea"));
		this.countries.add(new CountryMetaData(CountryCode.ER, LanguageCode.en + "|Eritrea", LanguageCode.de + "|Eriträa"));
		this.countries.add(new CountryMetaData(CountryCode.EE, LanguageCode.en + "|Estonia", LanguageCode.de + "|Estonien"));
		this.countries.add(new CountryMetaData(CountryCode.SZ, LanguageCode.en + "|Eswatini", LanguageCode.de + "|Eswatini"));
		this.countries.add(new CountryMetaData(CountryCode.ET, LanguageCode.en + "|Ethopia", LanguageCode.de + "|Äthiopien"));

		this.countries.add(new CountryMetaData(CountryCode.FK, LanguageCode.en + "|Falkland Islands", LanguageCode.de + "|Falklandinseln"));
		this.countries.add(new CountryMetaData(CountryCode.FO, LanguageCode.en + "|Faroe Islands", LanguageCode.de + "|Faröer Inseln"));
		this.countries.add(new CountryMetaData(CountryCode.FJ, LanguageCode.en + "|Fiji", LanguageCode.de + "|Fidschi"));
		this.countries.add(new CountryMetaData(CountryCode.FI, LanguageCode.en + "|Finland", LanguageCode.de + "|Finnland"));
		this.countries.add(new CountryMetaData(CountryCode.FR, LanguageCode.en + "|France", LanguageCode.de + "|Frankreich"));
		this.countries.add(new CountryMetaData(CountryCode.GF, LanguageCode.en + "|French Guinea", LanguageCode.de + "|Französisch Guinea"));
		this.countries.add(new CountryMetaData(CountryCode.PF, LanguageCode.en + "|Falkland Islands", LanguageCode.de + "|Französisch Polynesien"));
		this.countries.add(new CountryMetaData(CountryCode.TF, LanguageCode.en + "|French Southern Territories", LanguageCode.de + "|Französische Süd- und Antarktisgebiete"));

		this.countries.add(new CountryMetaData(CountryCode.GA, LanguageCode.en + "|Gabon", LanguageCode.de + "|Gabun"));
		this.countries.add(new CountryMetaData(CountryCode.GM, LanguageCode.en + "|Gambia", LanguageCode.de + "|Gambia"));
		this.countries.add(new CountryMetaData(CountryCode.GE, LanguageCode.en + "|Georgia", LanguageCode.de + "|Georgien"));
		this.countries.add(new CountryMetaData(CountryCode.DE, LanguageCode.en + "|Germany", LanguageCode.de + "|Deutschland"));
		this.countries.add(new CountryMetaData(CountryCode.GH, LanguageCode.en + "|Ghana", LanguageCode.de + "|Ghana"));
		this.countries.add(new CountryMetaData(CountryCode.GI, LanguageCode.en + "|Gibraltar", LanguageCode.de + "|Gibraltar"));
		this.countries.add(new CountryMetaData(CountryCode.GR, LanguageCode.en + "|Greece", LanguageCode.de + "|Griechenland"));
		this.countries.add(new CountryMetaData(CountryCode.GL, LanguageCode.en + "|Greenland", LanguageCode.de + "|Grönland"));
		this.countries.add(new CountryMetaData(CountryCode.GD, LanguageCode.en + "|Grnada", LanguageCode.de + "|Grenada"));
		this.countries.add(new CountryMetaData(CountryCode.GP, LanguageCode.en + "|Guadeloupe", LanguageCode.de + "|Guadeloupe"));
		this.countries.add(new CountryMetaData(CountryCode.GU, LanguageCode.en + "|Guam", LanguageCode.de + "|Guam"));
		this.countries.add(new CountryMetaData(CountryCode.GT, LanguageCode.en + "|Guatemala", LanguageCode.de + "|Guatemala"));
		this.countries.add(new CountryMetaData(CountryCode.GG, LanguageCode.en + "|Guernsey", LanguageCode.de + "|Guernsey"));
		this.countries.add(new CountryMetaData(CountryCode.GN, LanguageCode.en + "|Guinea", LanguageCode.de + "|Guinea"));
		this.countries.add(new CountryMetaData(CountryCode.GW, LanguageCode.en + "|Guinea-Bissau", LanguageCode.de + "|Guinea-Bissau"));
		this.countries.add(new CountryMetaData(CountryCode.GY, LanguageCode.en + "|Guyana", LanguageCode.de + "|Guyana"));

		this.countries.add(new CountryMetaData(CountryCode.HT, LanguageCode.en + "|Haiti", LanguageCode.de + "|Haiti"));
		this.countries.add(new CountryMetaData(CountryCode.HM, LanguageCode.en + "|Heard and McDonald Islands", LanguageCode.de + "|Heard und McDonaldinseln"));
		this.countries.add(new CountryMetaData(CountryCode.VA, LanguageCode.en + "|Vatican City", LanguageCode.de + "|Vatikanstadt"));
		this.countries.add(new CountryMetaData(CountryCode.HN, LanguageCode.en + "|Honduras", LanguageCode.de + "|Honduras"));
		this.countries.add(new CountryMetaData(CountryCode.HK, LanguageCode.en + "|Hong Kong", LanguageCode.de + "|Hong Kong"));
		this.countries.add(new CountryMetaData(CountryCode.HU, LanguageCode.en + "|Hungary", LanguageCode.de + "|Ungarn"));

		this.countries.add(new CountryMetaData(CountryCode.IS, LanguageCode.en + "|Iceland", LanguageCode.de + "|Island"));
		this.countries.add(new CountryMetaData(CountryCode.IN, LanguageCode.en + "|India", LanguageCode.de + "|Indien"));
		this.countries.add(new CountryMetaData(CountryCode.ID, LanguageCode.en + "|Indonesia", LanguageCode.de + "|Indonesien"));
		this.countries.add(new CountryMetaData(CountryCode.IR, LanguageCode.en + "|Iran", LanguageCode.de + "|Iran"));
		this.countries.add(new CountryMetaData(CountryCode.IQ, LanguageCode.en + "|Iraq", LanguageCode.de + "|Irak"));
		this.countries.add(new CountryMetaData(CountryCode.IE, LanguageCode.en + "|Ireland", LanguageCode.de + "|Irland"));
		this.countries.add(new CountryMetaData(CountryCode.IM, LanguageCode.en + "|Isle of Man", LanguageCode.de + "|Isle of Man"));
		this.countries.add(new CountryMetaData(CountryCode.IL, LanguageCode.en + "|Israel", LanguageCode.de + "|Israel"));
		this.countries.add(new CountryMetaData(CountryCode.IT, LanguageCode.en + "|Italy", LanguageCode.de + "|Italy"));

		this.countries.add(new CountryMetaData(CountryCode.JM, LanguageCode.en + "|Jamaica", LanguageCode.de + "|Jamaika"));
		this.countries.add(new CountryMetaData(CountryCode.JP, LanguageCode.en + "|Japan", LanguageCode.de + "|Japan"));
		this.countries.add(new CountryMetaData(CountryCode.JE, LanguageCode.en + "|Jersey", LanguageCode.de + "|Jersey"));
		this.countries.add(new CountryMetaData(CountryCode.JO, LanguageCode.en + "|Jordan", LanguageCode.de + "|Jordan"));

		this.countries.add(new CountryMetaData(CountryCode.KZ, LanguageCode.en + "|Kazakhstan", LanguageCode.de + "|Kasachstan"));
		this.countries.add(new CountryMetaData(CountryCode.KZ, LanguageCode.en + "|Kenya", LanguageCode.de + "|Kenia"));
		this.countries.add(new CountryMetaData(CountryCode.KZ, LanguageCode.en + "|Kiribati", LanguageCode.de + "|Kiribati"));
		this.countries.add(new CountryMetaData(CountryCode.KZ, LanguageCode.en + "|North Korea", LanguageCode.de + "|Nordkorea"));
		this.countries.add(new CountryMetaData(CountryCode.KZ, LanguageCode.en + "|South Korea", LanguageCode.de + "|Südkorea"));
		this.countries.add(new CountryMetaData(CountryCode.KZ, LanguageCode.en + "|Kuwait", LanguageCode.de + "|Kuwait"));
		this.countries.add(new CountryMetaData(CountryCode.KZ, LanguageCode.en + "|Kyrgyzstan", LanguageCode.de + "|Kirgistan"));

		this.countries.add(new CountryMetaData(CountryCode.LA, LanguageCode.en + "|Laos", LanguageCode.de + "|Laos"));
		this.countries.add(new CountryMetaData(CountryCode.LV, LanguageCode.en + "|Latvia", LanguageCode.de + "|Lettland"));
		this.countries.add(new CountryMetaData(CountryCode.LB, LanguageCode.en + "|Lebanon", LanguageCode.de + "|Libanon"));
		this.countries.add(new CountryMetaData(CountryCode.LS, LanguageCode.en + "|Lesotho", LanguageCode.de + "|Lesotho"));
		this.countries.add(new CountryMetaData(CountryCode.LR, LanguageCode.en + "|Liberia", LanguageCode.de + "|Liberia"));
		this.countries.add(new CountryMetaData(CountryCode.LY, LanguageCode.en + "|Libya", LanguageCode.de + "|Libyen"));
		this.countries.add(new CountryMetaData(CountryCode.LI, LanguageCode.en + "|Liechtenstein", LanguageCode.de + "|Liechtenstein"));
		this.countries.add(new CountryMetaData(CountryCode.LT, LanguageCode.en + "|Lithuania", LanguageCode.de + "|Litauen"));
		this.countries.add(new CountryMetaData(CountryCode.LU, LanguageCode.en + "|Luxembourg", LanguageCode.de + "|Luxemburg"));

		this.countries.add(new CountryMetaData(CountryCode.MO, LanguageCode.en + "|Macao", LanguageCode.de + "|Macau"));
		this.countries.add(new CountryMetaData(CountryCode.MG, LanguageCode.en + "|Madagscar", LanguageCode.de + "|Madagaskar"));
		this.countries.add(new CountryMetaData(CountryCode.MW, LanguageCode.en + "|Malawi", LanguageCode.de + "|Malawi"));
		this.countries.add(new CountryMetaData(CountryCode.MY, LanguageCode.en + "|Malaysia", LanguageCode.de + "|Malaysien"));
		this.countries.add(new CountryMetaData(CountryCode.MV, LanguageCode.en + "|Maldives", LanguageCode.de + "|Malediven"));
		this.countries.add(new CountryMetaData(CountryCode.ML, LanguageCode.en + "|Mali", LanguageCode.de + "|Mali"));
		this.countries.add(new CountryMetaData(CountryCode.MT, LanguageCode.en + "|Malta", LanguageCode.de + "|Malta"));
		this.countries.add(new CountryMetaData(CountryCode.MH, LanguageCode.en + "|Marshall Islands", LanguageCode.de + "|Marshallinseln"));
		this.countries.add(new CountryMetaData(CountryCode.MQ, LanguageCode.en + "|Martinique", LanguageCode.de + "|Martinique"));
		this.countries.add(new CountryMetaData(CountryCode.MR, LanguageCode.en + "|Mauritania", LanguageCode.de + "|Mauritanien"));
		this.countries.add(new CountryMetaData(CountryCode.MU, LanguageCode.en + "|Mauritius", LanguageCode.de + "|Mauritius"));
		this.countries.add(new CountryMetaData(CountryCode.YT, LanguageCode.en + "|Mayotte", LanguageCode.de + "|Mayotte"));
		this.countries.add(new CountryMetaData(CountryCode.MX, LanguageCode.en + "|Mexico", LanguageCode.de + "|Mexiko"));
		this.countries.add(new CountryMetaData(CountryCode.FM, LanguageCode.en + "|Micronesia", LanguageCode.de + "|Mikronesien"));
		this.countries.add(new CountryMetaData(CountryCode.MD, LanguageCode.en + "|Moldova", LanguageCode.de + "|Moldawien"));
		this.countries.add(new CountryMetaData(CountryCode.MC, LanguageCode.en + "|Monaco", LanguageCode.de + "|Monaco"));
		this.countries.add(new CountryMetaData(CountryCode.MN, LanguageCode.en + "|Mongolia", LanguageCode.de + "|Mongolien"));
		this.countries.add(new CountryMetaData(CountryCode.ME, LanguageCode.en + "|Montenegro", LanguageCode.de + "|Montenegro"));
		this.countries.add(new CountryMetaData(CountryCode.MS, LanguageCode.en + "|Montserrat", LanguageCode.de + "|Montserrat"));
		this.countries.add(new CountryMetaData(CountryCode.MA, LanguageCode.en + "|Morocco", LanguageCode.de + "|Marokko"));
		this.countries.add(new CountryMetaData(CountryCode.MZ, LanguageCode.en + "|Mozambique", LanguageCode.de + "|Mosambik"));
		this.countries.add(new CountryMetaData(CountryCode.MM, LanguageCode.en + "|Myanmar", LanguageCode.de + "|Myanmar"));

		this.countries.add(new CountryMetaData(CountryCode.NA, LanguageCode.en + "|Namibia", LanguageCode.de + "|Namibien"));
		this.countries.add(new CountryMetaData(CountryCode.NR, LanguageCode.en + "|Nauru", LanguageCode.de + "|Nauru"));
		this.countries.add(new CountryMetaData(CountryCode.NP, LanguageCode.en + "|Nepal", LanguageCode.de + "|Nepal"));
		this.countries.add(new CountryMetaData(CountryCode.NL, LanguageCode.en + "|Netherlands", LanguageCode.de + "|Niederlande"));
		this.countries.add(new CountryMetaData(CountryCode.NC, LanguageCode.en + "|New Caledonia", LanguageCode.de + "|Neukaledonien"));
		this.countries.add(new CountryMetaData(CountryCode.NZ, LanguageCode.en + "|New Zealand", LanguageCode.de + "|Neuseeland"));
		this.countries.add(new CountryMetaData(CountryCode.NI, LanguageCode.en + "|Nicaragua", LanguageCode.de + "|Nicaragua"));
		this.countries.add(new CountryMetaData(CountryCode.NE, LanguageCode.en + "|Niger", LanguageCode.de + "|Niger"));
		this.countries.add(new CountryMetaData(CountryCode.NG, LanguageCode.en + "|Nigeria", LanguageCode.de + "|Nigeria"));
		this.countries.add(new CountryMetaData(CountryCode.NU, LanguageCode.en + "|Niue", LanguageCode.de + "|Niue"));
		this.countries.add(new CountryMetaData(CountryCode.NF, LanguageCode.en + "|Norfolk Island", LanguageCode.de + "|Norfolkinsel"));
		this.countries.add(new CountryMetaData(CountryCode.MP, LanguageCode.en + "|Northern Mariana Islands", LanguageCode.de + "|Nördliche Marianen"));
		this.countries.add(new CountryMetaData(CountryCode.NO, LanguageCode.en + "|Norway", LanguageCode.de + "|Norwegen"));

		this.countries.add(new CountryMetaData(CountryCode.OM, LanguageCode.en + "|Oman", LanguageCode.de + "|Oman"));

		this.countries.add(new CountryMetaData(CountryCode.PK, LanguageCode.en + "|Pakistan", LanguageCode.de + "|Pakistan"));
		this.countries.add(new CountryMetaData(CountryCode.PW, LanguageCode.en + "|Palau", LanguageCode.de + "|Palau"));
		this.countries.add(new CountryMetaData(CountryCode.PS, LanguageCode.en + "|Palestine", LanguageCode.de + "|Palästina"));
		this.countries.add(new CountryMetaData(CountryCode.PA, LanguageCode.en + "|Panama", LanguageCode.de + "|Panama"));
		this.countries.add(new CountryMetaData(CountryCode.PG, LanguageCode.en + "|Papua New Guinea", LanguageCode.de + "|Papua Neuguinea"));
		this.countries.add(new CountryMetaData(CountryCode.PY, LanguageCode.en + "|Paraguay", LanguageCode.de + "|Paraguay"));
		this.countries.add(new CountryMetaData(CountryCode.PE, LanguageCode.en + "|Peru", LanguageCode.de + "|Peru"));
		this.countries.add(new CountryMetaData(CountryCode.PH, LanguageCode.en + "|Philippines", LanguageCode.de + "|Philippinen"));
		this.countries.add(new CountryMetaData(CountryCode.PN, LanguageCode.en + "|Pitcairn Islands", LanguageCode.de + "|Pitcairninseln"));
		this.countries.add(new CountryMetaData(CountryCode.PL, LanguageCode.en + "|Poland", LanguageCode.de + "|Polen"));
		this.countries.add(new CountryMetaData(CountryCode.PT, LanguageCode.en + "|Portugal", LanguageCode.de + "|Portugal"));
		this.countries.add(new CountryMetaData(CountryCode.PR, LanguageCode.en + "|Puerto Rico", LanguageCode.de + "|Puerto Rico"));

		this.countries.add(new CountryMetaData(CountryCode.QA, LanguageCode.en + "|Qatar", LanguageCode.de + "|Katar"));

		this.countries.add(new CountryMetaData(CountryCode.MK, LanguageCode.en + "|Northern Macedonia", LanguageCode.de + "|Nordmazedonien"));
		this.countries.add(new CountryMetaData(CountryCode.RO, LanguageCode.en + "|Romania", LanguageCode.de + "|Rumänien"));
		this.countries.add(new CountryMetaData(CountryCode.RU, LanguageCode.en + "|Russia", LanguageCode.de + "|Russland"));
		this.countries.add(new CountryMetaData(CountryCode.RW, LanguageCode.en + "|Rwanda", LanguageCode.de + "|Ruanda"));
		this.countries.add(new CountryMetaData(CountryCode.RE, LanguageCode.en + "|Réunion", LanguageCode.de + "|Réunion"));

		this.countries.add(new CountryMetaData(CountryCode.BL, LanguageCode.en + "|Saint Barthélemy", LanguageCode.de + "|Sankt Bartholomäus"));
		this.countries.add(new CountryMetaData(CountryCode.SH, LanguageCode.en + "|Saint Helena, Ascension and Tristan da Cunha", LanguageCode.de + "|St. Helena, Ascension und Tristan da Cunha"));
		this.countries.add(new CountryMetaData(CountryCode.KN, LanguageCode.en + "|Saint Kitts and Nevis", LanguageCode.de + "|St. Kitts und Nevis"));
		this.countries.add(new CountryMetaData(CountryCode.LC, LanguageCode.en + "|Saint Lucia", LanguageCode.de + "|St. Lucia"));
		this.countries.add(new CountryMetaData(CountryCode.MF, LanguageCode.en + "|Saint Martin", LanguageCode.de + "|Saint Martin"));
		this.countries.add(new CountryMetaData(CountryCode.PM, LanguageCode.en + "|Saint Pierre and Miquelon", LanguageCode.de + "|St. Pierre und Miquelon"));
		this.countries.add(new CountryMetaData(CountryCode.VC, LanguageCode.en + "|Saint Vincent and the Grenadines", LanguageCode.de + "|St. Vincent und die Grenadinen"));
		this.countries.add(new CountryMetaData(CountryCode.WS, LanguageCode.en + "|Samoa", LanguageCode.de + "|Samoa"));
		this.countries.add(new CountryMetaData(CountryCode.SM, LanguageCode.en + "|San Marino", LanguageCode.de + "|San Marino"));
		this.countries.add(new CountryMetaData(CountryCode.ST, LanguageCode.en + "|Sao Tome and Principe", LanguageCode.de + "|Sao Tome und Principe"));
		this.countries.add(new CountryMetaData(CountryCode.SA, LanguageCode.en + "|Saudi Arabia", LanguageCode.de + "|Saudi Arabien"));
		this.countries.add(new CountryMetaData(CountryCode.SN, LanguageCode.en + "|Senegal", LanguageCode.de + "|Senegal"));
		this.countries.add(new CountryMetaData(CountryCode.RS, LanguageCode.en + "|Serbia", LanguageCode.de + "|Serbien"));
		this.countries.add(new CountryMetaData(CountryCode.SC, LanguageCode.en + "|Seychelles", LanguageCode.de + "|Seyschellen"));
		this.countries.add(new CountryMetaData(CountryCode.SL, LanguageCode.en + "|Sierra Leone", LanguageCode.de + "|Sierra Leone"));
		this.countries.add(new CountryMetaData(CountryCode.SG, LanguageCode.en + "|Singapore", LanguageCode.de + "|Singapur"));
		this.countries.add(new CountryMetaData(CountryCode.SX, LanguageCode.en + "|Sint Maarten", LanguageCode.de + "|Sint Maarten"));
		this.countries.add(new CountryMetaData(CountryCode.SK, LanguageCode.en + "|Slovakia", LanguageCode.de + "|Slovakei"));
		this.countries.add(new CountryMetaData(CountryCode.SI, LanguageCode.en + "|Slovania", LanguageCode.de + "|Slowenien"));
		this.countries.add(new CountryMetaData(CountryCode.SB, LanguageCode.en + "|Solomon Islands", LanguageCode.de + "|Salomon-Inseln"));
		this.countries.add(new CountryMetaData(CountryCode.SO, LanguageCode.en + "|Somalia", LanguageCode.de + "|Somalien"));
		this.countries.add(new CountryMetaData(CountryCode.ZA, LanguageCode.en + "|South Africa", LanguageCode.de + "|Südafrika"));
		this.countries.add(new CountryMetaData(CountryCode.GS, LanguageCode.en + "|South Georgia and the South Sandwich Islands", LanguageCode.de + "|Südgeorgien und die Südlichen Sandwichinseln"));
		this.countries.add(new CountryMetaData(CountryCode.SS, LanguageCode.en + "|South Sudan", LanguageCode.de + "|Südsudan"));
		this.countries.add(new CountryMetaData(CountryCode.ES, LanguageCode.en + "|Spain", LanguageCode.de + "|Spanien"));
		this.countries.add(new CountryMetaData(CountryCode.LK, LanguageCode.en + "|Sri Lanka", LanguageCode.de + "|Sri Lanka"));
		this.countries.add(new CountryMetaData(CountryCode.SD, LanguageCode.en + "|Sudan", LanguageCode.de + "|Sudan"));
		this.countries.add(new CountryMetaData(CountryCode.SR, LanguageCode.en + "|Suriname", LanguageCode.de + "|Suriname"));
		this.countries.add(new CountryMetaData(CountryCode.SJ, LanguageCode.en + "|Svalbard and Jan Mayen", LanguageCode.de + "|Svalbard und Jan Mayen"));
		this.countries.add(new CountryMetaData(CountryCode.SE, LanguageCode.en + "|Sweden", LanguageCode.de + "|Sweden"));
		this.countries.add(new CountryMetaData(CountryCode.CH, LanguageCode.en + "|Switzerland", LanguageCode.de + "|Schweiz"));
		this.countries.add(new CountryMetaData(CountryCode.SY, LanguageCode.en + "|Syria", LanguageCode.de + "|Syrien"));

		this.countries.add(new CountryMetaData(CountryCode.TW, LanguageCode.en + "|Taiwan", LanguageCode.de + "|Taiwan"));
		this.countries.add(new CountryMetaData(CountryCode.TJ, LanguageCode.en + "|Tajikistan", LanguageCode.de + "|Tadschikistan"));
		this.countries.add(new CountryMetaData(CountryCode.TZ, LanguageCode.en + "|Tanzania", LanguageCode.de + "|Tansania"));
		this.countries.add(new CountryMetaData(CountryCode.TH, LanguageCode.en + "|Thailand", LanguageCode.de + "|Thailand"));
		this.countries.add(new CountryMetaData(CountryCode.TL, LanguageCode.en + "|Timor-Leste", LanguageCode.de + "|Osttimor"));
		this.countries.add(new CountryMetaData(CountryCode.TG, LanguageCode.en + "|Togo", LanguageCode.de + "|Togo"));
		this.countries.add(new CountryMetaData(CountryCode.TK, LanguageCode.en + "|Tokelau", LanguageCode.de + "|Tokelau"));
		this.countries.add(new CountryMetaData(CountryCode.TO, LanguageCode.en + "|Tonga", LanguageCode.de + "|Tonga"));
		this.countries.add(new CountryMetaData(CountryCode.TT, LanguageCode.en + "|Trinidad and Tobago", LanguageCode.de + "|Trinidad und Tobago"));
		this.countries.add(new CountryMetaData(CountryCode.TN, LanguageCode.en + "|Tunisia", LanguageCode.de + "|Tunesien"));
		this.countries.add(new CountryMetaData(CountryCode.TR, LanguageCode.en + "|Turkey", LanguageCode.de + "|Türkei"));
		this.countries.add(new CountryMetaData(CountryCode.TM, LanguageCode.en + "|Turkenmistan", LanguageCode.de + "|Turkmenistan"));
		this.countries.add(new CountryMetaData(CountryCode.TC, LanguageCode.en + "|Turks and Caicos Islands", LanguageCode.de + "|Turks- und Caicosinseln"));
		this.countries.add(new CountryMetaData(CountryCode.TV, LanguageCode.en + "|Tuvalu", LanguageCode.de + "|Tuvalu"));

		this.countries.add(new CountryMetaData(CountryCode.UG, LanguageCode.en + "|Uganda", LanguageCode.de + "|Uganda"));
		this.countries.add(new CountryMetaData(CountryCode.UA, LanguageCode.en + "|Ukraine", LanguageCode.de + "|Ukraine"));
		this.countries.add(new CountryMetaData(CountryCode.AE, LanguageCode.en + "|United Arab Emirates", LanguageCode.de + "|Vereinigte Arabische Emirate"));
		this.countries.add(new CountryMetaData(CountryCode.GB, LanguageCode.en + "|United Kingdom|UK", LanguageCode.de + "|Vereinigtes Königreich|Großbritannien"));
		this.countries.add(new CountryMetaData(CountryCode.UM, LanguageCode.en + "|US Minor Outlying Islands", LanguageCode.de + "|Kleinere Amerikanische Überseeinseln"));
		this.countries.add(new CountryMetaData(CountryCode.US, LanguageCode.en + "|United States of America|USA|U.S.A.", LanguageCode.de + "|Vereinigte Staaten von Amerika"));
		this.countries.add(new CountryMetaData(CountryCode.UY, LanguageCode.en + "|Uruguay", LanguageCode.de + "|Uruguay"));
		this.countries.add(new CountryMetaData(CountryCode.UZ, LanguageCode.en + "|Uzbekistan", LanguageCode.de + "|Usbekistan"));

		this.countries.add(new CountryMetaData(CountryCode.VU, LanguageCode.en + "|Vanuatu", LanguageCode.de + "|Vanuatu"));
		this.countries.add(new CountryMetaData(CountryCode.VE, LanguageCode.en + "|Venezuela", LanguageCode.de + "|Venezuela"));
		this.countries.add(new CountryMetaData(CountryCode.VN, LanguageCode.en + "|Vietnam", LanguageCode.de + "|Vietnam"));
		this.countries.add(new CountryMetaData(CountryCode.VG, LanguageCode.en + "|British Virgin Islands", LanguageCode.de + "|Britische Jungferninseln"));
		this.countries.add(new CountryMetaData(CountryCode.VI, LanguageCode.en + "|United States Virgin Islands", LanguageCode.de + "|Amerikanische Jungferninseln"));

		this.countries.add(new CountryMetaData(CountryCode.WF, LanguageCode.en + "|Wallis and Futuna", LanguageCode.de + "|Wallis und Futuna"));
		this.countries.add(new CountryMetaData(CountryCode.EH, LanguageCode.en + "|Western Sahara", LanguageCode.de + "|Westsahara"));

		this.countries.add(new CountryMetaData(CountryCode.YE, LanguageCode.en + "|Yemen", LanguageCode.de + "|Jemen"));

		this.countries.add(new CountryMetaData(CountryCode.ZM, LanguageCode.en + "|Zambia", LanguageCode.de + "|Sambia"));
		this.countries.add(new CountryMetaData(CountryCode.ZW, LanguageCode.en + "|Zimbabwe", LanguageCode.de + "|Simbabwe"));

		this.countries.add(new CountryMetaData(CountryCode.AX, LanguageCode.en + "|Åland Islands", LanguageCode.de + "|Ålandinseln"));
	}

}
