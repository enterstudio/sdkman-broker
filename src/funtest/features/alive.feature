Feature: Alive
	Scenario: Database is healthy
		Given an initialised database
		When a request is made for "/health/alive"
		Then the service response status is 200

	Scenario: Database is inconsistent
		Given an uninitialised database
		When a request is made for "/health/alive"
		Then the service response status is 503

 #can't implement because of infrastructure
	@manual
	Scenario: Database is inaccessible
		Given an inaccessible database
		When a request is made for "/health/alive"
		Then the service response status is 503
