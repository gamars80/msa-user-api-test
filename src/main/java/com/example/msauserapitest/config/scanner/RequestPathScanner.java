package com.example.msauserapitest.config.scanner;


import com.example.msauserapitest.user.enums.RoleType;

import java.util.Map;
import java.util.Set;

public interface RequestPathScanner {
    Map<RoleType, Set<String>> scanRequestMethods();
}
