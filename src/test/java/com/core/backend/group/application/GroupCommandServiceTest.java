package com.core.backend.group.application;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.group.GroupServiceTestFixture;
import com.core.backend.group.application.dto.GroupRegisterServiceRequest;
import com.core.backend.group.domain.Group;
import com.core.backend.user.domain.User;

class GroupCommandServiceTest extends GroupServiceTestFixture {

	@Test
	@DisplayName("모임방을 생성한다.")
	void registerGroupTest() {
		// given
		User user = User.of("test@email.com", "password", "test", "01011111111");
		userRepository.save(user);

		AuthUser authUser = new AuthUser(user.getId());

		GroupRegisterServiceRequest request = GroupRegisterServiceRequest.from("testGroup");

		// when
		groupCommandService.createGroup(authUser.getUserId(), request);

		// then
		Group testGroup = groupRepository.findAll().stream()
			.filter(group -> group.getGroupName().equals(request.getGroupName()))
			.findFirst()
			.orElse(null);

		SoftAssertions.assertSoftly(softAssertions -> {
			Assertions.assertThat(testGroup).isNotNull();
			Assertions.assertThat(testGroup.getMembers())
				.extracting(userGroup -> userGroup.getUser())
				.contains(user);
		});
	}
}
