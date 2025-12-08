var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "146250",
        "ok": "140277",
        "ko": "5973"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "39"
    },
    "maxResponseTime": {
        "total": "25684",
        "ok": "25684",
        "ko": "10999"
    },
    "meanResponseTime": {
        "total": "1505",
        "ok": "1386",
        "ko": "4313"
    },
    "standardDeviation": {
        "total": "3054",
        "ok": "3010",
        "ko": "2736"
    },
    "percentiles1": {
        "total": "8",
        "ok": "8",
        "ko": "4409"
    },
    "percentiles2": {
        "total": "1410",
        "ok": "943",
        "ko": "6574"
    },
    "percentiles3": {
        "total": "9004",
        "ok": "9019",
        "ko": "8784"
    },
    "percentiles4": {
        "total": "12281",
        "ok": "12368",
        "ko": "9865"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 103904,
    "percentage": 71
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 3341,
    "percentage": 2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 33032,
    "percentage": 23
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 5973,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "294.859",
        "ok": "282.817",
        "ko": "12.042"
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
        "ok": "140277",
        "ko": "5973"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "39"
    },
    "maxResponseTime": {
        "total": "25684",
        "ok": "25684",
        "ko": "10999"
    },
    "meanResponseTime": {
        "total": "1505",
        "ok": "1386",
        "ko": "4313"
    },
    "standardDeviation": {
        "total": "3054",
        "ok": "3010",
        "ko": "2736"
    },
    "percentiles1": {
        "total": "8",
        "ok": "8",
        "ko": "4409"
    },
    "percentiles2": {
        "total": "1411",
        "ok": "944",
        "ko": "6574"
    },
    "percentiles3": {
        "total": "9004",
        "ok": "9019",
        "ko": "8784"
    },
    "percentiles4": {
        "total": "12281",
        "ok": "12368",
        "ko": "9865"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 103904,
    "percentage": 71
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 3341,
    "percentage": 2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 33032,
    "percentage": 23
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 5973,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "294.859",
        "ok": "282.817",
        "ko": "12.042"
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
