package domain.db;

import domain.model.TestResult;

import java.util.List;

public interface TestResultDB {
    void add(TestResult testResult);
    List<TestResult> get(String userid);
    TestResult getLatestTestResult(String userid);
}