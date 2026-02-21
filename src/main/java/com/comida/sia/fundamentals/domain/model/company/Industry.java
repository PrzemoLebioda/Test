package com.comida.sia.fundamentals.domain.model.company;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum Industry {
	//TECHNOLOGY
	SEMICONDUCTORS ("Semiconductors"),
	SOFTWARE_INFRASTRUCTURE ("Software - Infrastructure"),
	CONSUMER_ELECTRONICS ("Consumer Electronics"),
	SOFTWARE_APPLICATION ("Software - Application"),
	INFORMATION_TECHNOLOGY_SERVICES ("Information Technology Services"),
	SEMICONDUCTOR_EQUIPMENT_AND_MATERIALS ("Semiconductor Equipment & Materials"),
	COMMUNICATION_EQUIPMENT ("Communication Equipment"),
	COMPUTER_HARDWARE ("Computer Hardware"),
	ELECTRONIC_COMPONENTS ("Electronic Components"),
	SCIENTIFIC_AND_TECHNICAL_INSTRUMENTS ("Scientific & Technical Instruments"),
	SOLAR ("Solar"),
	ELECTRONICS_AND_COMPUTER_DISTRIBUTION ("Electronics & Computer Distribution"),
	
	//FINANCIAL_SERVICES,
	BANKS_DIVERSIFIED("Banks - Diversified"),
	CREDIT_SERVICES("Credit Services"),
	ASSET_MANAGEMENT("Asset Management"),
	CAPITAL_MARKETS("Capital Markets"),
	INSURANCE_DIVERSIFIED("Insurance - Diversified"),
	BANKS_REGIONAL("Banks - Regional"),
	FINANCIAL_DATA_AND_STOCK_EXCHANGES("Financial Data & Stock Exchanges"),
	INSURANCE_PROPERTY_AND_CASUALTY("Insurance - Property & Casualty"),
	INSURANCE_BROKERS("Insurance Brokers"),
	INSURANCE_LIFE("Insurance - Life"),
	INSURANCE_SPECIALTY("Insurance - Specialty"),
	MORTGAGE_FINANCE("Mortgage Finance"),
	INSURANCE_REINSURANCE("Insurance - Reinsurance"),
	FINANCIAL_CONGLOMERATES("Financial Conglomerates"),
	SHELL_COMPANIES("Shell Companies"),
	
	//CONSUMER_CYCLICAL
	INTERNET_RETAIL("Internet Retail"),
	AUTO_MANUFACTURERS("Auto Manufacturers"),
	RESTAURANTS("Restaurants"),
	HOME_IMPROVEMENT_RETAIL("Home Improvement Retail"),
	TRAVEL_SERVICES("Travel Services"),
	APPAREL_RETAIL("Apparel Retail"),
	AUTO_PARTS("Auto Parts"),
	SPECIALTY_RETAIL("Specialty Retail"),
	RESIDENTIAL_CONSTRUCTION("Residential Construction"),
	AUTO_AND_TRUCK_DEALERSHIPS("Auto & Truck Dealerships"),
	LODGING("Lodging"),
	PACKAGING_AND_CONTAINERS("Packaging & Containers"),
	FOOTWEAR_AND_ACCESSORIES("Footwear & Accessories"),
	RESORTS_AND_CASINOS("Resorts & Casinos"),
	GAMBLING("Gambling"),
	LEISURE("Leisure"),
	FURNISHINGS_FIXTURES_AND_APPLIANCES("Furnishings, Fixtures & Appliances"),
	APPAREL_MANUFACTURING("Apparel Manufacturing"),
	PERSONAL_SERVICES("Personal Services"),
	LUXURY_GOODS("Luxury Goods"),
	RECREATIONAL_VEHICLES("Recreational Vehicles"),
	DEPARTMENT_STORES("Department Stores"),
	TEXTILE_MANUFACTURING("Textile Manufacturing"),
	
	//COMMUNICATION_SERVICES
	INTERNET_CONTENT_AND_INFORMATION("Internet Content & Information"),
	TELECOM_SERVICES("Telecom Services"),
	ENTERTAINMENT("Entertainment"),
	ADVERTISING_AGENCIES("Advertising Agencies"),
	ELECTRONIC_GAMING_AND_MULTIMEDIA("Electronic Gaming & Multimedia"),
	BROADCASTING("Broadcasting"),
	PUBLISHING("Publishing"),
	
	//HEALTHCARE
	DRUG_MANUFACTURERS_GENERAL("Drug Manufacturers - General"),
	BIOTECHNOLOGY("Biotechnology"),
	MEDICAL_DEVICES("Medical Devices"),
	DIAGNOSTICS_AND_RESEARCH("Diagnostics & Research"),
	HEALTHCARE_PLANS("Healthcare Plans"),
	MEDICAL_INSTRUMENTS_AND_SUPPLIES("Medical Instruments & Supplies"),
	MEDICAL_CARE_FACILITIES("Medical Care Facilities"),
	MEDICAL_DISTRIBUTION("Medical Distribution"),
	DRUG_MANUFACTURERS_SPECIALTY_AND_GENERIC("Drug Manufacturers - Specialty & Generic"),
	HEALTH_INFORMATION_SERVICES("Health Information Services"),
	PHARMACEUTICAL_RETAILERS("Pharmaceutical Retailers"),
	
	//INDUSTRIALS
	AEROSPACE_AND_DEFENSE("Aerospace & Defense"),
	SPECIALTY_INDUSTRIAL_MACHINERY("Specialty Industrial Machinery"),
	FARM_AND_HEAVY_CONSTRUCTION_MACHINERY("Farm & Heavy Construction Machinery"),
	RAILROADS("Railroads"),
	ENGINEERING_AND_CONSTRUCTION("Engineering & Construction"),
	BUILDING_PRODUCTS_AND_EQUIPMENT("Building Products & Equipment"),
	SPECIALTY_BUSINESS_SERVICES("Specialty Business Services"),
	INDUSTRIAL_DISTRIBUTION("Industrial Distribution"),
	CONGLOMERATES("Conglomerates"),
	WASTE_MANAGEMENT("Waste Management"),
	INTEGRATED_FREIGHT_AND_LOGISTICS("Integrated Freight & Logistics"),
	ELECTRICAL_EQUIPMENT_AND_PARTS("Electrical Equipment & Parts"),
	RENTAL_AND_LEASING_SERVICES("Rental & Leasing Services"),
	AIRLINES("Airlines"),
	TRUCKING("Trucking"),
	CONSULTING_SERVICES("Consulting Services"),
	TOOLS_AND_ACCESSORIES("Tools & Accessories"),
	METAL_FABRICATION("metal fabrication"),
	POLLUTION_AND_TREATMENT_CONTROLS("Pollution & Treatment Controls"),
	SECURITY_AND_PROTECTION_SERVICES("Security & Protection Services"),
	MARINE_SHIPPING("Marine Shipping"),
	AIRPORTS_AND_AIR_SERVICES("Airports & Air Services"),
	STAFFING_AND_EMPLOYMENT_SERVICES("Staffing & Employment Services"),
	BUSINESS_EQUIPMENT_AND_SUPPLIES("Business Equipment & Supplies"),
	INFRASTRUCTURE_OPERATIONS("Infrastructure Operations"),
	
	//CONSUMER_DEFENSIVE
	DISCOUNT_STORES("Discount Stores"),
	BEVERAGES_NON_ALCOHOLIC("Beverages - Non-Alcoholic"),
	HOUSEHOLD_PERSONAL_PRODUCTS("Household & Personal Products"),
	TOBACCO("Tobacco"),
	PACKAGED_FOODS("Packaged Foods"),
	CONFECTIONERS("Confectioners"),
	FARM_PRODUCTS("Farm Products"),
	FOOD_DISTRIBUTION("Food Distribution"),
	GROCERY_STORES("Grocery Stores"),
	BEVERAGES_BREWERS("Beverages - Brewers"),
	EDUCATION_AND_TRAINING_SERVICES("Education & Training Services"),
	BEVERAGES_WINERIES_AND_DISTILLERIES("Beverages - Wineries & Distilleries"),
	
	//ENERGY
	OIL_AND_GAS_INTEGRATED("Oil & Gas Integrated"),
	OIL_AND_GAS_MIDSTREAM("Oil & Gas Midstream"),
	OIL_AND_GAS_EP("Oil & Gas E&P"),
	OIL_AND_GAS_EQUIPMENT_AND_SERVICES("Oil & Gas Equipment & Services"),
	OIL_AND_GAS_REFINING_AND_MARKETING("Oil & Gas Refining & Marketing"),
	URANIUM("Uranium"),
	OIL_AND_GAS_DRILLING("Oil & Gas Drilling"),
	THERMAL_COAL("Thermal Coal"),
	
	//BASIC_MATERIALS
	SPECIALTY_CHEMICALS("Specialty Chemicals"),
	GOLD("Gold"),
	BUILDING_MATERIALS("Building Materials"),
	COPPER("Copper"),
	STEEL("Steel"),
	AGRICULTURAL_INPUTS("Agricultural Inputs"),
	OTHER_INDUSTRIAL_METALS_AND_MINING("Other Industrial Metals & Mining"),
	CHEMICALS("Chemicals"),
	OTHER_PRECIOUS_METALS_AND_MINING("Other Precious Metals & Mining"),
	LUMBER_AND_WOOD_PRODUCTION("Lumber & Wood Production"),
	ALUMINUM("Aluminum"),
	SILVER("Silver"),
	COKING_COAL("Coking Coal"),
	PAPER_AND_PAPER_PRODUCTS("Paper & Paper Products"),

	//REAL_ESTATE
	REIT_SPECIALTY("REIT - Specialty"),
	REIT_INDUSTRIAL("REIT - Industrial"),
	REIT_HEALTHCARE_FACILITIES("REIT - Healthcare Facilities"),
	REIT_RETAIL("REIT - Retail"),
	REIT_RESIDENTIAL("REIT - Residential"),
	REAL_ESTATE_SERVICES("Real Estate Services"),
	REIT_MORTGAGE("REIT - Mortgage"),
	REIT_OFFICE("REIT - Office"),
	REIT_DIVERSIFIED("REIT - Diversified"),
	REIT_HOTEL_AND_MOTEL("REIT - Hotel & Motel"),
	REAL_ESTATE_DEVELOPMENT("Real Estate - Development"),
	REAL_ESTATE_DIVERSIFIED("Real Estate - Diversified"),
	
	//UTILITIES
	UTILITIES_REGULATED_ELECTRIC("Utilities - Regulated Electric"),
	UTILITIES_INDEPENDENT_POWER_PRODUCERS("Utilities - Independent Power Producers"),
	UTILITIES_REGULATED_GAS("Utilities - Regulated Gas"),
	UTILITIES_DIVERSIFIED("Utilities - Diversified"),
	UTILITIES_REGULATED_WATER("Utilities - Regulated Water"),
	UTILITIES_RENEWABLE("Utilities - Renewable");
	
	@Getter private String name;
	private static final Map<String, Industry> ENUM_MAP;
	
	private Industry(String name) {
		this.name = name;
	}
	
	static {
        Map<String, Industry> map = new ConcurrentHashMap<String, Industry>();
        for (Industry instance : Industry.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Industry get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
