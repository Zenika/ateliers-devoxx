package webserver

import (
	"fmt"
	"net/http"
)

func handler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Hello there %s!", r.URL.Path[1:])
}

func webserver() {
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8080", nil)
}
