var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "86250",
        "ok": "82685",
        "ko": "3565"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "43"
    },
    "maxResponseTime": {
        "total": "9957",
        "ok": "9957",
        "ko": "7081"
    },
    "meanResponseTime": {
        "total": "679",
        "ok": "596",
        "ko": "2606"
    },
    "standardDeviation": {
        "total": "1385",
        "ok": "1319",
        "ko": "1467"
    },
    "percentiles1": {
        "total": "9",
        "ok": "8",
        "ko": "2655"
    },
    "percentiles2": {
        "total": "564",
        "ok": "447",
        "ko": "3780"
    },
    "percentiles3": {
        "total": "4137",
        "ok": "3962",
        "ko": "4933"
    },
    "percentiles4": {
        "total": "5797",
        "ok": "5791",
        "ko": "5827"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 69853,
    "percentage": 81
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1663,
    "percentage": 2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 11169,
    "percentage": 13
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3565,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "359.375",
        "ok": "344.521",
        "ko": "14.854"
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
        "total": "86250",
        "ok": "82685",
        "ko": "3565"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "43"
    },
    "maxResponseTime": {
        "total": "9957",
        "ok": "9957",
        "ko": "7081"
    },
    "meanResponseTime": {
        "total": "679",
        "ok": "596",
        "ko": "2606"
    },
    "standardDeviation": {
        "total": "1385",
        "ok": "1319",
        "ko": "1467"
    },
    "percentiles1": {
        "total": "9",
        "ok": "8",
        "ko": "2655"
    },
    "percentiles2": {
        "total": "564",
        "ok": "448",
        "ko": "3780"
    },
    "percentiles3": {
        "total": "4137",
        "ok": "3962",
        "ko": "4933"
    },
    "percentiles4": {
        "total": "5797",
        "ok": "5791",
        "ko": "5827"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 69853,
    "percentage": 81
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1663,
    "percentage": 2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 11169,
    "percentage": 13
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3565,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "359.375",
        "ok": "344.521",
        "ko": "14.854"
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
