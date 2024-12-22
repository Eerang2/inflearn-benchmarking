package green.study.domain.admin.model;

import green.study.domain.admin.entity.AdminEntity;
import green.study.domain.enums.UserType;
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
    private String name;
    private UserType type;

    public static Admin from(AdminEntity entity) {
        return Admin.builder()
                .id(entity.getId())
                .adminId(entity.getAdminId())
                .password(entity.getPassword())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }

    public AdminEntity toEntity() {
        return AdminEntity.builder()
                .id(id)
                .adminId(adminId)
                .password(password)
                .name(name)
                .type(type)
                .build();

    }
}
