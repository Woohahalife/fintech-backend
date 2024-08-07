package com.core.backend.group.application;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.group.GroupServiceTestFixture;
import com.core.backend.group.domain.Group;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.user.domain.User;

class GroupQueryServiceTest extends GroupServiceTestFixture {

	@Test
	@DisplayName("사용자가 참여한 방목록을 조회할 수 있다.")
	void getAllGroupTest() {
		// given
		User user = User.of("test@email.com", "password", "test", "01011111111");
		userRepository.save(user);

		AuthUser authUser = new AuthUser(user.getId());

		Group group1 = setGroupData("testGroup1", user);
		Group group2 = setGroupData("testGroup2", user);
		Group group3 = setGroupData("testGroup3", user);

		groupRepository.saveAll(List.of(group1, group2, group3));

		// when
		List<GroupInfoResponse> responses = groupQueryService.getAllGroup(authUser.getUserId());

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(responses).hasSize(3);
			softAssertions.assertThat(responses)
				.extracting("groupName", "numOfParticipantCount")
				.containsExactly(
					Tuple.tuple("testGroup1", 1),
					Tuple.tuple("testGroup2", 1),
					Tuple.tuple("testGroup3", 1)
				);
		});
	}

	private Group setGroupData(String testGroup1, User user) {
		Group group = Group.builder()
			.groupName(testGroup1)
			.build();

		group.addUserToGroup(user);

		return group;
	}
}
