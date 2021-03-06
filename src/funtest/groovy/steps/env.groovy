package steps

import wslite.rest.RESTClient

import static cucumber.api.groovy.Hooks.After
import static support.MongoHelper.clean
import static support.MongoHelper.prepareDB

appBaseUrl = "http://localhost:5050"
httpClient = new RESTClient(appBaseUrl)

if(!binding.hasVariable("db")) {
    db = prepareDB()
}

After { scenario ->
    clean(db)
}