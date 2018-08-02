package infra.converters;

import java.math.BigDecimal;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.business.ICommitmentRepository;
import domain.business.IJsonConverter;
import domain.model.Commitment;
import domain.model.Payment;
import infra.utilitary.DateTransformation;

public class PaymentToJsonConverter implements IJsonConverter<Payment>{

	private ICommitmentRepository commitmentRepository;
	
	public PaymentToJsonConverter(ICommitmentRepository commitmentRepository) {
		this.commitmentRepository = commitmentRepository;
	}

	@Override
	public Payment jsonToDomain(JSONArray jsonArray) {
		
		JSONObject paymentJson = jsonArray.getJSONObject(0).getJSONObject("agrupador");
		
		int id = paymentJson.getInt("id");
		BigDecimal amountPaid = paymentJson.getBigDecimal("amountPaid");
		Date payDay;
		try {
			payDay = DateTransformation.StringToDate(paymentJson.getString("payDay"));
		} catch (Exception e) {
			payDay = null;
		}
		Commitment commitment = commitmentRepository.findById(paymentJson.getInt("commitmentId"));
		
		return new Payment(id, payDay, commitment, amountPaid);
	}

	@Override
	public JSONArray domainToJson(Payment payment) {
		
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonHeader = new JSONObject();
		
		jsonObject.put("id", payment.getId());
		jsonObject.put("amountPaid", payment.getAmountPaid());
		jsonObject.put("payDay", payment.getPayDay());
		jsonObject.put("commitmentId", payment.getCommitmentId());
		
		jsonHeader.put("payment", jsonObject);
					
		return new JSONArray().put(jsonHeader);
	}
}
