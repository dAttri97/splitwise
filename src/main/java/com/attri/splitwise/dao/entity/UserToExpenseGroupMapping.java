package com.attri.splitwise.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Table(name = "user_to_expense_group_mapping")
public class UserToExpenseGroupMapping extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "group_id")
    private Long groupId;
}
