package com.core.backend.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.core.backend.group.application.GroupCommandService;
import com.core.backend.group.application.GroupQueryService;
import com.core.backend.group.infra.JpaGroupRepository;
import com.core.backend.user.domain.repository.UserRepository;

@ActiveProfiles("test")
@SpringBootTest
public abstract class GroupServiceTestFixture {

	@Autowired
	protected GroupCommandService groupCommandService;

	@Autowired
	protected GroupQueryService groupQueryService;

	@Autowired
	protected JpaGroupRepository groupRepository;

	@Autowired
	protected UserRepository userRepository;
}
