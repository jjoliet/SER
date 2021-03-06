define({ "api": [
  {
    "type": "get",
    "url": "rest/pb/:phonebook/get?first=:first&last=:last",
    "title": "Get list of entrys in a given phonebook that contain the given query string for first and last.",
    "name": "getPartial",
    "group": "PhoneBook",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phonebook",
            "description": "<p>Id of the phonebook being retrieved. Use &quot;ul&quot; for unlisted phone entries.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "first",
            "description": "<p>The first name being queried (can be empty-&gt; ex: ?first=&amp;last=ley</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "last",
            "description": "<p>The last name being queried (can be empty - &gt; ex: ?first=joh</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "\tHTTP/1.1 200 OK\n\t[\n  {\"firstName\":\"Johnson\",\"lastName\":\"Denhamley\", \"phone\": \"5551234\" \"id\": \"-1\"},\n  {\"firstName\":\"John\",\"lastName\":\"Worsley\", \"phone\": \"5525222\" \"id\": \"5\"}\n ]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneBookResource.java",
    "groupTitle": "PhoneBook",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "rest/pb/:phonebook",
    "title": "Get list of entrys in a given phonebook",
    "name": "getPhoneBook",
    "group": "PhoneBook",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phonebook",
            "description": "<p>Id of the phonebook being retrieved. Use &quot;ul&quot; for unlisted phone entries.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "\tHTTP/1.1 200 OK\n\t[\n  {\"firstName\":\"Ariel\",\"lastName\":\"Denham\", \"phone\": \"5551234\" \"id\": \"-1\"},\n  {\"firstName\":\"John\",\"lastName\":\"Worsley\", \"phone\": \"5525222\" \"id\": \"5\"}\n ]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneBookResource.java",
    "groupTitle": "PhoneBook",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  },
  {
    "type": "Delete",
    "url": "rest/entry?num=:num",
    "title": "Delete an Entry",
    "name": "DeleteEntry",
    "group": "PhoneEntry",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "num",
            "description": "<p>Phone number attached to an entry.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "PAYLOAD: Text/plain\nString format-> Ariel Denheam 5551234 (id optional here)\n\tHTTP/1.1 204 No Content\n\t[]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "PhoneEntry",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  },
  {
    "type": "Post",
    "url": "rest/entry",
    "title": "Create A new Entry",
    "name": "createEntry",
    "group": "PhoneEntry",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "PAYLOAD: application/json ex: {\"firstName\":\"Ariel\",\"lastName\":\"Denham\", \"phone\": \"5551234\"}\n\tHTTP/1.1 201 Created\n\t[\n  {\"firstName\":\"Ariel\",\"lastName\":\"Denham\", \"phone\": \"5551234\"}\n ]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "PhoneEntry",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "rest/entry",
    "title": "Get list of PhoneEntrys",
    "name": "getEntries",
    "group": "PhoneEntry",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "\tHTTP/1.1 200 OK\n\t[\n  {\"firstName\":\"Ariel\",\"lastName\":\"Denham\", \"phone\": \"5551234\"},\n  {\"firstName\":\"John\",\"lastName\":\"Worsley\", \"phone\":\"5554321\"}\n ]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "PhoneEntry",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "rest/entry/:phoneNumber",
    "title": "Get a specific Phone Entry",
    "name": "getEntry",
    "group": "PhoneEntry",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phoneNumber",
            "description": "<p>Phone number attached to an entry.</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "\tHTTP/1.1 200 OK\n\t[\n  {\"firstName\":\"Ariel\",\"lastName\":\"Denham\", \"phone\": \"5551234\"}\n ]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "PhoneEntry",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  },
  {
    "type": "PUT",
    "url": "rest/entry",
    "title": "Update an Entry",
    "name": "updateEntry",
    "group": "PhoneEntry",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "PAYLOAD: application/json ex: {\"firstName\":\"Ariel\",\"lastName\":\"Denham\", \"phone\": \"5551234\"}\n\tHTTP/1.1 201 Created\n\t[\n  {\"firstName\":\"Ariel\",\"lastName\":\"Denham\", \"phone\": \"5551234\"}\n ]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/lab3/resources/PhoneEntryResource.java",
    "groupTitle": "PhoneEntry",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          },
          {
            "group": "Error 4xx",
            "type": "404",
            "optional": false,
            "field": "NotFound",
            "description": "<p>Activity cannot be found</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  }
] });
