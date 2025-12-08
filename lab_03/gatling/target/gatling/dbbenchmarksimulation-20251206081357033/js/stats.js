var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "146250",
        "ok": "139902",
        "ko": "6348"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "32"
    },
    "maxResponseTime": {
        "total": "9601",
        "ok": "9601",
        "ko": "8028"
    },
    "meanResponseTime": {
        "total": "734",
        "ok": "651",
        "ko": "2577"
    },
    "standardDeviation": {
        "total": "1589",
        "ok": "1524",
        "ko": "1868"
    },
    "percentiles1": {
        "total": "6",
        "ok": "3",
        "ko": "2403"
    },
    "percentiles2": {
        "total": "192",
        "ok": "14",
        "ko": "4078"
    },
    "percentiles3": {
        "total": "4845",
        "ok": "4708",
        "ko": "5895"
    },
    "percentiles4": {
        "total": "6373",
        "ok": "6329",
        "ko": "6773"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 115177,
    "percentage": 79
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2439,
    "percentage": 2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 22286,
    "percentage": 15
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6348,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "301.546",
        "ok": "288.458",
        "ko": "13.089"
    }
},
contents: {
"req_get-user-by-id-edd10": {
        type: "REQUEST",
        name: "get_user_by_id",
path: "get_user_by_id",
pathFormatted: "req_get-user-by-id-edd10",
stats: {
    "name": "get_user_by_id",
    "numberOfRequests": {
        "total": "146250",
        "ok": "139902",
        "ko": "6348"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "32"
    },
    "maxResponseTime": {
        "total": "9601",
        "ok": "9601",
        "ko": "8028"
    },
    "meanResponseTime": {
        "total": "734",
        "ok": "651",
        "ko": "2577"
    },
    "standardDeviation": {
        "total": "1589",
        "ok": "1524",
        "ko": "1868"
    },
    "percentiles1": {
        "total": "6",
        "ok": "3",
        "ko": "2403"
    },
    "percentiles2": {
        "total": "193",
        "ok": "14",
        "ko": "4078"
    },
    "percentiles3": {
        "total": "4845",
        "ok": "4708",
        "ko": "5895"
    },
    "percentiles4": {
        "total": "6373",
        "ok": "6329",
        "ko": "6773"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 115177,
    "percentage": 79
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2439,
    "percentage": 2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 22286,
    "percentage": 15
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6348,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "301.546",
        "ok": "288.458",
        "ko": "13.089"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
