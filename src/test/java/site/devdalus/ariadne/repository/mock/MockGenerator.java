package site.devdalus.ariadne.repository.mock;

import site.devdalus.ariadne.constant.LoginType;
import site.devdalus.ariadne.constant.PermissionName;
import site.devdalus.ariadne.constant.RoleName;
import site.devdalus.ariadne.domain.Group;
import site.devdalus.ariadne.domain.Permission;
import site.devdalus.ariadne.domain.Role;
import site.devdalus.ariadne.domain.User;

public class MockGenerator {

    public static User getMockUser() {
        return User.builder()
                .id("id")
                .password("password")
                .nickname("nickname")
                .loginType(LoginType.DEFAULT)
                .build();
    }

    public static Group getMockGroup() {
        return Group.builder()
                .groupName("groupName")
                .build();
    }

    public static Role getMockRole() {
        return Role.builder()
                .roleName(RoleName.ADMIN)
                .build();
    }

    public static Permission getMockPermission() {
        return Permission.builder()
                .permissionName(PermissionName.READ)
                .build();
    }

}