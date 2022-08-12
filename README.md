# AUTO1 json api tests

Easiest way to execute tests on environment:

- specify ```urls.base_url``` param in ```application.properties``` file,
  like ```urls.base_url=http://env.url.net```
- run ```./gradlew clean test``` from root

To run tests in Idea, Lombok plugin should be installed
To see Request/Responses set logger.spring.level=DEBUG in log4j2.properties
JUnit Test report will be generated in ```build/reports/tests/test/index.html```

Found Defects:

1. Wrong status code for Create new user requst: expected 201, actual -200
2. Wrong status code for Delete user requst: expected 204, actual -200
3. Wrong status code for Create new car: expected 201, actual -200
4. Wrong status code for Delete car requst: expected 204, actual -200
5. Empty response body for Update car by id PUT request
6. 500 exception if try to delete already deleted user/car, expected 404 NOT FOUND
6. 500 exception if try to update user/car, expected 404 NOT FOUND
7. Users with same name and email can be created (?)