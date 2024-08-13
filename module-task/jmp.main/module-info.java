module jmp.main {
    requires jmp.dto;
    requires jmp.bank.api;
    requires jmp.service.api;

    uses com.bank.api.Bank;
    uses com.service.api.Service;
}