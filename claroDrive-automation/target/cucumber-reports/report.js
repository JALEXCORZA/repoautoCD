$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/Landing.feature");
formatter.feature({
  "name": "Test the landing portal",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@LandingTest"
    }
  ]
});
formatter.scenario({
  "name": "promo",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@LandingTest"
    },
    {
      "name": "@promo"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Navigate in Claro Drive",
  "keyword": "Given "
});
formatter.match({
  "location": "steps.LandingSteps.navigateInClaroDrive()"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.step({
  "name": "Validate promo",
  "keyword": "And "
});
formatter.match({
  "location": "steps.LandingSteps.validatePromo()"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.after({
  "error_message": "java.lang.NullPointerException: Cannot invoke \"org.asynchttpclient.ws.WebSocket.sendCloseFrame(int, String)\" because \"this.socket\" is null\r\n\tat org.openqa.selenium.remote.http.netty.NettyWebSocket.close(NettyWebSocket.java:116)\r\n\tat org.openqa.selenium.devtools.Connection.close(Connection.java:127)\r\n\tat java.base/java.util.Optional.ifPresent(Optional.java:178)\r\n\tat org.openqa.selenium.chromium.ChromiumDriver.quit(ChromiumDriver.java:192)\r\n\tat pages.utils.SeleniumDriver.tearDown(SeleniumDriver.java:83)\r\n\tat steps.AfterActions.tearDown(AfterActions.java:26)\r\n",
  "status": "failed"
});
});