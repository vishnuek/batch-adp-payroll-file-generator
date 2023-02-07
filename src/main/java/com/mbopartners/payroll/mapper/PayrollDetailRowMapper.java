package com.mbopartners.payroll.mapper;

import com.mbopartners.payroll.entity.PayrollDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayrollDetailRowMapper implements RowMapper<PayrollDetail> {

    @Override
    public PayrollDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        PayrollDetail payrollDetail = new PayrollDetail();
        payrollDetail.setMemberNumber(rs.getString("MEMBER_NUMBER"));
        payrollDetail.setPayrollDate(rs.getDate("PAYROLL_DATE").toLocalDate());
        payrollDetail.setBDisabilityInsuranceAmount(rs.getDouble("B_DISABILITYINS"));
        payrollDetail.setGrossAmount(rs.getDouble("GROSSPAYROLL"));
        payrollDetail.setNetAmount(rs.getDouble("NETPAYROLL"));
        payrollDetail.setLivedInCode(rs.getString("LIVED_CODE"));
        payrollDetail.setWorkedInCode(rs.getString("WORKED_CODE"));
        payrollDetail.setFederalWithholdingAmount(rs.getDouble("FEDWHAM"));
        payrollDetail.setStateWithholdingAmount(rs.getDouble("STATEWHAM"));
        payrollDetail.setLStateWithholdingAmount(rs.getDouble("LSTATEWHAM"));
        payrollDetail.setCountyWithholdingAmount(rs.getDouble("COUNTYWHAM"));
        payrollDetail.setCityWithholdingAmount(rs.getDouble("CITYWHAM"));
        payrollDetail.setFederalExemptAmount(rs.getDouble("FEDEXAM"));
        payrollDetail.setBeneficiaryRegisteredFlag(rs.getBoolean("BENEFICIARY_REGISTEREDFLAG"));
        payrollDetail.setJMedicalInsuranceAmount(rs.getDouble("J_MEDICALINS"));
        payrollDetail.setBDisabilityInsuranceAmount(rs.getDouble("B_DISABILITYINS"));
        payrollDetail.setILifeInsuranceAmount(rs.getDouble("I_LIFEINS"));
        payrollDetail.setDDentailInsuranceAmount(rs.getDouble("D_DENTALINS"));
        payrollDetail.setElevenRoth401kAmount(rs.getDouble("ELEVEN_ROTH_401K"));
        payrollDetail.setKEmployee401kAmount(rs.getDouble("K_EMPLOYEE401K"));
        payrollDetail.setNNetAdvanceAmount(rs.getDouble("N_NETADVANCE"));
        payrollDetail.setFrequency(rs.getString("FREQUENCY"));
        payrollDetail.setGGarnishment1Amount(rs.getDouble("G_GARNISHMENT1"));
        payrollDetail.setFiftyOneGarnish2Amount(rs.getDouble("FIFTYONE_GARNISHMENT2"));
        payrollDetail.setFiftyTwoGarnish3Amount(rs.getDouble("FIFTYTWO_GARNISHMENT3"));
        payrollDetail.setFiftyThreeGarnish4Amount(rs.getDouble("FIFTYTHREE_GARNISHMENT4"));
        payrollDetail.setFiftyFourGarnish5Amount(rs.getDouble("FIVEFOUR_GARNISHMENT5"));
        payrollDetail.setL401kLoan2Amount(rs.getDouble("L_401K_LOAN2"));
        payrollDetail.setFortyOne401kLoan1Amount(rs.getDouble("FORTYONE_401K_LOAN1"));
        payrollDetail.setDPayrollAdd2Amount(rs.getDouble("D_PAYROLLADD2"));
        payrollDetail.setCAmex1401Amount(rs.getDouble("C_AMEX1401"));
        payrollDetail.setRAmex2989Amount(rs.getDouble("R_AMEX2989"));
        payrollDetail.setMMboPmt1429Amount(rs.getDouble("M_MBOPMT1429"));
        payrollDetail.setTTtPmt1430Amount(rs.getDouble("T_TTPMT1430"));
        payrollDetail.setPSuppliAmount(rs.getDouble("P_SUPPLI"));
        payrollDetail.setQSupplispAmount(rs.getDouble("Q_SUPPLISP"));
        payrollDetail.setQpParkingPreAmount(rs.getDouble("QP_PARKING_PRE"));
        payrollDetail.setQppParkingPostAmount(rs.getDouble("QPP_PARKING_POST"));
        payrollDetail.setQtTransitPreAmount(rs.getDouble("QT_TRANSIT_PRE"));
        payrollDetail.setQtpTransitPostAmount(rs.getDouble("QTP_TRANSIT_POST"));
        payrollDetail.setFLocalServicesTaxAmount(rs.getDouble("F_LST"));
        payrollDetail.setSuidiAmount(rs.getDouble("SUIDI"));
        payrollDetail.setMieAmount(rs.getDouble("MIE"));
        payrollDetail.setMisAmount(rs.getDouble("MIS"));
        payrollDetail.setMicAmount(rs.getDouble("MIC"));
        payrollDetail.setMifAmount(rs.getDouble("MIF"));
        payrollDetail.setCieAmount(rs.getDouble("CIE"));
        payrollDetail.setCisAmount(rs.getDouble("CIS"));
        payrollDetail.setCicAmount(rs.getDouble("CIC"));
        payrollDetail.setCifAmount(rs.getDouble("CIF"));
        return payrollDetail;
    }
}
