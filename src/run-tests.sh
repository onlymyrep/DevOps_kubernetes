#!/bin/bash

# Wait for services to start
sleep 30

# Run Newman tests
newman run application_tests.postman_collection.json \
  -e postman_environment.json \
  --reporters cli,json \
  --reporter-json-export test-results.json

# Check test results
if jq -e '.run.failures | length == 0' test-results.json > /dev/null; then
    echo "All tests passed!"
    exit 0
else
    echo "Some tests failed!"
    jq '.run.failures' test-results.json
    exit 1
fi
