var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "86250",
        "ok": "82664",
        "ko": "3586"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "35"
    },
    "maxResponseTime": {
        "total": "8999",
        "ok": "8999",
        "ko": "5990"
    },
    "meanResponseTime": {
        "total": "404",
        "ok": "327",
        "ko": "2180"
    },
    "standardDeviation": {
        "total": "1124",
        "ok": "1040",
        "ko": "1478"
    },
    "percentiles1": {
        "total": "6",
        "ok": "5",
        "ko": "1881"
    },
    "percentiles2": {
        "total": "9",
        "ok": "9",
        "ko": "3363"
    },
    "percentiles3": {
        "total": "3361",
        "ok": "2929",
        "ko": "4950"
    },
    "percentiles4": {
        "total": "5197",
        "ok": "5125",
        "ko": "5636"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 74895,
    "percentage": 87
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 906,
    "percentage": 1
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 6863,
    "percentage": 8
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3586,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "359.375",
        "ok": "344.433",
        "ko": "14.942"
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
        "ok": "82664",
        "ko": "3586"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "35"
    },
    "maxResponseTime": {
        "total": "8999",
        "ok": "8999",
        "ko": "5990"
    },
    "meanResponseTime": {
        "total": "404",
        "ok": "327",
        "ko": "2180"
    },
    "standardDeviation": {
        "total": "1124",
        "ok": "1040",
        "ko": "1478"
    },
    "percentiles1": {
        "total": "6",
        "ok": "5",
        "ko": "1881"
    },
    "percentiles2": {
        "total": "9",
        "ok": "9",
        "ko": "3363"
    },
    "percentiles3": {
        "total": "3361",
        "ok": "2929",
        "ko": "4950"
    },
    "percentiles4": {
        "total": "5197",
        "ok": "5125",
        "ko": "5636"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 74895,
    "percentage": 87
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 906,
    "percentage": 1
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 6863,
    "percentage": 8
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3586,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "359.375",
        "ok": "344.433",
        "ko": "14.942"
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
