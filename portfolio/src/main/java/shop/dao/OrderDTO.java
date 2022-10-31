package shop.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
	String orderNum;
	String orderDate;
	String buyerName;
	String buyerPhone ;
	String buyerEmail ;
	String receiverName ;
	String receiverPostcode;
	String receiverStreetAddress;
	String receiverAddressDetail;
	String receiverPhone ;
	String receiverEmail;
	String codeList ;
	String countList ;
	String payment;
	String memo ;
	String depositor ;
	String bank ;
	String typeReceiptUse ;
	Integer nPrice;
	Integer sPrice;
}
