package green.study.domain.admin.model;

import green.study.domain.admin.entity.AdminEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {

    private Long id;
    private String adminId;
    private String password;

    public static Admin from(AdminEntity entity) {
        return Admin.builder()
                .id(entity.getId())
                .adminId(entity.getAdminId())
                .password(entity.getPassword())
                .build();
    }

    public AdminEntity toEntity() {
        return AdminEntity.builder()
                .id(id)
                .adminId(adminId)
                .password(password)
                .build();

    }
}
