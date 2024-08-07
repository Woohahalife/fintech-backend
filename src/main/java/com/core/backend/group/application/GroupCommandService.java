package com.core.backend.group.application;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import com.core.backend.common.exception.ErrorCode;import com.core.backend.group.application.dto.GroupRegisterServiceRequest;import com.core.backend.group.domain.Group;import com.core.backend.group.domain.repository.GroupRepository;import com.core.backend.user.domain.User;import com.core.backend.user.domain.repository.UserRepository;import com.core.backend.user.exception.UserException;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;@Slf4j@Service@Transactional@RequiredArgsConstructorpublic class GroupCommandService {	private final GroupRepository groupRepository;	private final UserRepository userRepository;	public void createGroup(Long authUser, GroupRegisterServiceRequest request) {		User user = userRepository.findById(authUser);		Group group = Group.builder()			.groupName(request.getGroupName())			.build();		group.addUserToGroup(user);		groupRepository.save(group);	}}