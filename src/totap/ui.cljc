(ns totap.ui)

(def views
  [{:id :apps
    :text "Apps"
    :href "/"}
   {:id :support
    :text "Support"
    :href "/support"}])

(defn navbar []
  [:div.navbar.bg-base-100.shadow-sm
   [:div.flex-1
    [:a.btn.btn-ghost.text-xl "totap"]]
   [:div.flex-none
    [:ul.menu.menu-horizontal.px-1
     (for [{:keys [text href]} views]
       [:li
        [:a {:href href} text]])]]])

(defn footer []
  [:footer.footer.sm:footer-horizontal.footer-center.bg-base-300.text-base-content.p-4.mt-auto
   [:aside
    [:p "© 2026 totap"]]])

(defn support []
  [:div.m-8.flex.flex-col.items-center.justify-center
   [:h1.text-2xl "Support"]
   [:p.mt-4
    "If you have any questions or need help, please contact us at "
    [:a.text-blue-500 {:href "mailto:"} "feedback@totap.de"]]])

(defn apps []
  [:div.m-8.flex.flex-col.items-center.justify-center
   [:h1.text-2xl.pb-8 "Apps"]
   [:div.card.w-full.max-w-sm.md:max-w-xl.md:card-side.bg-base-300
    [:figure
     [:picture
      [:source {:srcset "/assets/focus-only-icon-dark.png"
                :media "(prefers-color-scheme: dark)"}]
      [:img.w-full.h-auto.max-w-48.block
       {:src "/assets/focus-only-icon-light.png"
        :alt "Focus Only Icon"}]]]
    [:div.card-body.items-center.text-center
     [:h2.card-title.justify-center.w-full.text-center "Focus Only"]
     [:p "A simple focus timer that helps you stay productive."]]]])

(defn render [state]
  (let [current-view (:current-view state)]
    [:div.flex.flex-col.min-h-screen
     (navbar)
     (case current-view
       :route/apps
       (apps)

       :route/support
       (support))
     (footer)]))
