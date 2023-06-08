(defproject cljtime "0.9.0-SNAPSHOT"
  :description "UNIX Time Tools in Pure Clojure"
  :url "http://lavagoatsoftware.com/clljtime"
  :license {:name "GPL-3.0"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main ^:skip-aot cljtime.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
  :plugins [[dev.weavejester/lein-cljfmt "0.10.4"]]
