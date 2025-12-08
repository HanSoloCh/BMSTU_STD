var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "103125",
        "ok": "102515",
        "ko": "610"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "56"
    },
    "maxResponseTime": {
        "total": "2756",
        "ok": "2756",
        "ko": "1968"
    },
    "meanResponseTime": {
        "total": "50",
        "ok": "45",
        "ko": "876"
    },
    "standardDeviation": {
        "total": "213",
        "ok": "202",
        "ko": "379"
    },
    "percentiles1": {
        "total": "2",
        "ok": "2",
        "ko": "916"
    },
    "percentiles2": {
        "total": "7",
        "ok": "7",
        "ko": "1121"
    },
    "percentiles3": {
        "total": "419",
        "ok": "78",
        "ko": "1461"
    },
    "percentiles4": {
        "total": "1158",
        "ok": "1073",
        "ko": "1706"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 100456,
    "percentage": 97
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1243,
    "percentage": 1
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 816,
    "percentage": 1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 610,
    "percentage": 1
},
    "meanNumberOfRequestsPerSecond": {
        "total": "257.17",
        "ok": "255.648",
        "ko": "1.521"
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
        "total": "103125",
        "ok": "102515",
        "ko": "610"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "56"
    },
    "maxResponseTime": {
        "total": "2756",
        "ok": "2756",
        "ko": "1968"
    },
    "meanResponseTime": {
        "total": "50",
        "ok": "45",
        "ko": "876"
    },
    "standardDeviation": {
        "total": "213",
        "ok": "202",
        "ko": "379"
    },
    "percentiles1": {
        "total": "2",
        "ok": "2",
        "ko": "916"
    },
    "percentiles2": {
        "total": "7",
        "ok": "7",
        "ko": "1121"
    },
    "percentiles3": {
        "total": "419",
        "ok": "78",
        "ko": "1461"
    },
    "percentiles4": {
        "total": "1158",
        "ok": "1073",
        "ko": "1706"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 100456,
    "percentage": 97
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1243,
    "percentage": 1
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 816,
    "percentage": 1
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 610,
    "percentage": 1
},
    "meanNumberOfRequestsPerSecond": {
        "total": "257.17",
        "ok": "255.648",
        "ko": "1.521"
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
