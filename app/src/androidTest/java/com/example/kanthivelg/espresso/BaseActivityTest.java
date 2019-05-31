package com.example.kanthivelg.espresso;

import android.Manifest;
import android.support.test.rule.GrantPermissionRule;

import org.junit.Rule;
import org.junit.rules.TestName;

class BaseActivityTest {
    @Rule
    public GrantPermissionRule permissionRule =
            GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @Rule public TestName name = new TestName();

    public CustomFailureHandler customFailureHandler;
}
