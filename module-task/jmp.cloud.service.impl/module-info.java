module jmp.cloud.service.impl {
	requires jmp.dto;
	requires transitive jmp.service.api;

    provides com.service.api.Service with com.infra.service.impl.CloudService;
}