module jmp.cloud.bank.impl {
	requires jmp.dto;
	requires transitive jmp.bank.api;

    provides com.bank.api.Bank with com.infra.bank.impl.RetailBank, com.infra.bank.impl.InvestmentBank, com.infra.bank.impl.CentralBank;
}