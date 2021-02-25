package org.zk.dubbo.consumer;

import com.com.tims.lvmama.product.service.OrderInvoiceInfoService;
import com.com.tims.lvmama.product.vo.OrderInvoiceApplyInfo;
import com.google.common.annotations.VisibleForTesting;
import com.tims.invoice.api.IInvoiceRemoteService;
import com.tims.invoice.api.consts.PurchaseWay;
import com.tims.invoice.api.param.ApplyInvoiceParam;
import com.tims.invoice.api.param.OrderInvoiceParam;
import com.tims.invoice.api.param.PageableRequestParams;
import com.tims.invoice.api.vo.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * 消费者，调用服务好像调用本地一样
 */
public class Consumer {

	ClassPathXmlApplicationContext context;
	IInvoiceRemoteService invoiceRemoteService;
	OrderInvoiceInfoService orderInvoiceInfoService;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		context.start();
		invoiceRemoteService = (IInvoiceRemoteService) context.getBean("invoiceRemoteService");
		orderInvoiceInfoService = (OrderInvoiceInfoService) context.getBean("orderInvoiceInfoService");
	}


	@Test
	public void searchInvoice() {
		PageableRequestParams<OrderInvoiceParam> pageReq = new PageableRequestParams<OrderInvoiceParam>();
		OrderInvoiceParam param = new OrderInvoiceParam();
		param.setOrderId(21100000780L);
		pageReq.setParam(param);
		PageVo<OrderDetailInvoiceVo> pageVo = invoiceRemoteService.searchInvoice(pageReq);
		System.out.println(pageVo);
	}

	@Test
	public void applyInvoice() {
		ApplyInvoiceParam applyInvoiceParam = new ApplyInvoiceParam();
		applyInvoiceParam.setOrderId(21100000780L);
		applyInvoiceParam.setPurchaseWay(PurchaseWay.company);
		applyInvoiceParam.setTitle("上海驴途信息科技有限公司");
		applyInvoiceParam.setTaxNumber("91310114055880631E");
		applyInvoiceParam.setContent("旅游服务费");
		applyInvoiceParam.setMemo("这是备注");
		applyInvoiceParam.setReceiveEmail("zhangkang@lvmama.com");
		Result result = invoiceRemoteService.applyInvoice(applyInvoiceParam);
		System.out.println(result);
	}

	@Test
	public void invoiceApplyInfo() {
		OrderInvoiceApplyInfo orderInvoiceApplyInfo = orderInvoiceInfoService.queryOrderInvoiceApplyInfo(21100000780L);
		System.out.println(orderInvoiceApplyInfo);
	}

	@Test
	public void getInvoiceableAmount() {
		Long amount  = invoiceRemoteService.getInvoiceableAmount(21100000780L);
		System.out.println(amount);
	}

	@Test
	public void discard() {
		RedElecInvoiceVO redElecInvoiceVO = new RedElecInvoiceVO();
		redElecInvoiceVO.setInvoiceOrderMergeIds(Arrays.asList("2102031506382217756"));
		redElecInvoiceVO.setRemark("作废");
		Result result = invoiceRemoteService.discard(redElecInvoiceVO);
		System.out.println(result);
	}

	@Test
	public void discardReject() {
		RedElecInvoiceVO redElecInvoiceVO = new RedElecInvoiceVO();
		redElecInvoiceVO.setInvoiceOrderMergeIds(Arrays.asList("2102031449251602019"));
		redElecInvoiceVO.setRemark("作废驳回");
		invoiceRemoteService.discardReject(redElecInvoiceVO);
	}

	@Test
	public void discardPass() {
		RedElecInvoiceVO redElecInvoiceVO = new RedElecInvoiceVO();
		redElecInvoiceVO.setInvoiceOrderMergeIds(Arrays.asList("2102041735575232182"));
		redElecInvoiceVO.setRemark("作废审核通过");
		Result result = invoiceRemoteService.discardPass(redElecInvoiceVO);
		System.out.println(result);
	}

	@Test
	public void refund() {
		Result result = invoiceRemoteService.orderRefundAutoDiscard(21100000780L);
		System.out.println(result);
	}


}
