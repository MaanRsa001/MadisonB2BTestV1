package com.maan.adminnew.Travel.Service;
import java.util.List;
import java.util.Map;

import com.maan.adminnew.Travel.DAO.TravelBean;
import com.maan.adminnew.Travel.DAO.TravelDAO;

public class TravelService {
	TravelDAO travelDAO=new TravelDAO();
	
	public List<Object> getSchemeValue(TravelBean travelBean)
	{
		return travelDAO.getSchemeValue(travelBean);
	}
	public List<Object> getOptionValue(TravelBean travelBean)
	{
		return travelDAO.getOptionValue(travelBean);
	}
	public List<Object> getCoverValue(TravelBean travelBean)
	{
		return travelDAO.getCoverValue(travelBean);
	}
	
	//SCHEME
	public List<Object> getScheme(TravelBean travelBean)
	{
		return travelDAO.getScheme(travelBean);
	}
	public List<Object> getSchemeHistory(TravelBean travelBean)
	{
		return travelDAO.getSchemeHistory(travelBean);
	}
	public String editScheme(TravelBean travelBean)
	{
		return travelDAO.editScheme(travelBean);
	}
	public String insertScheme(TravelBean travelBean)
	{
		return travelDAO.insertScheme(travelBean);
	}
	//OPTION
	public List<Object> getOption(TravelBean travelBean)
	{
		return travelDAO.getOption(travelBean);
	}
	public List<Object> getOptionHistory(TravelBean travelBean)
	{
		return travelDAO.getOptionHistory(travelBean);
	}
	public String editOption(TravelBean travelBean)
	{
		return travelDAO.editOption(travelBean);
	}
	public String insertOption(TravelBean travelBean)
	{
		return travelDAO.insertOption(travelBean);
	}
	//MASTER COVERAGE
	public List<Object> getCoverage(TravelBean travelBean)
	{
		return travelDAO.getCoverage(travelBean);
	}
	public List<Object> getCoverageHistory(TravelBean travelBean)
	{
		return travelDAO.getCoverageHistory(travelBean);
	}
	public String editCoverage(TravelBean travelBean)
	{
		return travelDAO.editCoverage(travelBean);
	}
	public String insertCoverage(TravelBean travelBean)
	{
		return travelDAO.insertCoverage(travelBean);
	}
	//DISCOUNT RATE
	public List<Object> getDiscountRate(TravelBean travelBean)
	{
		return travelDAO.getDiscountRate(travelBean);
	}
	public List<Object> getDiscountRateHistory(TravelBean travelBean)
	{
		return travelDAO.getDiscountRateHistory(travelBean);
	}
	public String editDiscountRate(TravelBean travelBean)
	{
		return travelDAO.editDiscountRate(travelBean);
	}
	public String insertDiscountRate(TravelBean travelBean)
	{
		return travelDAO.insertDiscountRate(travelBean);
	}
	//RELATION DISCOUNT
	public List<Object> getRelationDiscount(TravelBean travelBean)
	{
		return travelDAO.getRelationDiscount(travelBean);
	}
	public List<Object> getRelationDiscountHistory(TravelBean travelBean)
	{
		return travelDAO.getRelationDiscountHistory(travelBean);
	}
	public String editRelationDiscount(TravelBean travelBean)
	{
		return travelDAO.editRelationDiscount(travelBean);
	}
	public String insertRelationDiscount(TravelBean travelBean)
	{
		return travelDAO.insertRelationDiscount(travelBean);
	}
	//PREMIUM RATE
	public List<Object> getPremiumRate(TravelBean travelBean)
	{
		return travelDAO.getPremiumRate(travelBean);
	}
	public List<Object> getPremiumRateHistory(TravelBean travelBean)
	{
		return travelDAO.getPremiumRateHistory(travelBean);
	}
	public String editPremiumRate(TravelBean travelBean)
	{
		return travelDAO.editPremiumRate(travelBean);
	}
	public String insertPremiumRate(TravelBean travelBean)
	{
		return travelDAO.insertPremiumRate(travelBean);
	}
	//LINK OPTION
	public List<Object> getLinkOption(TravelBean travelBean)
	{
		return travelDAO.getLinkOption(travelBean);
	}
	public List<Object> getLinkOptionHistory(TravelBean travelBean)
	{
		return travelDAO.getLinkOptionHistory(travelBean);
	}
	public String editLinkOption(TravelBean travelBean)
	{
		return travelDAO.editLinkOption(travelBean);
	}
	public String insertLinkOption(TravelBean travelBean)
	{
		return travelDAO.insertLinkOption(travelBean);
	}
	//LINK COVERAGE
	public List<Object> getLinkCoverage(TravelBean travelBean)
	{
		return travelDAO.getLinkCoverage(travelBean);
	}
	public List<Object> getLinkCoverageHistory(TravelBean travelBean)
	{
		return travelDAO.getLinkCoverageHistory(travelBean);
	}
	public String editLinkCoverage(TravelBean travelBean)
	{
		return travelDAO.editLinkCoverage(travelBean);
	}
	public String insertLinkCoverage(TravelBean travelBean)
	{
		return travelDAO.insertLinkCoverage(travelBean);
	}
	public List<Map<String,Object>> getschemename(TravelBean travelBean) {
		return travelDAO.getschemename(travelBean);
	}
	public List<Map<String, Object>> getOptionName(TravelBean travelBean) {
		return travelDAO.getOptionName(travelBean);
	}
	public List<Map<String, Object>> TravelTypeList(TravelBean travelBean) {
		return travelDAO.TravelTypeList(travelBean);
	}
	public List<Map<String, Object>> TypeList(TravelBean travelBean) {
		return travelDAO.TypeList(travelBean);
	}
	public int coreappcodetest(String code, TravelBean travelBean) {
		return travelDAO.coreappcodetest(code,travelBean);
	}
	public int coreappcodetestoption(TravelBean travelBean) {
		return travelDAO.coreappcodetestoption(travelBean);
	}
	public int coreappcodetestcoverage(TravelBean travelBean) {
		return travelDAO.coreappcodetestcoverage(travelBean);
	}
	public int coreappcodetestdiscount(TravelBean travelBean) {
		return travelDAO.coreappcodetestdiscount(travelBean);
	}
	public int coreappcodetestrelation(TravelBean travelBean) {
		return travelDAO.coreappcodetestrelation(travelBean);
	}
	public int coreappcodetestpremium(TravelBean travelBean) {
		return travelDAO.coreappcodetestpremium(travelBean);
	}

}
