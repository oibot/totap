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
    [:p "Copyright © 2026 - All right reserved"]]])

(defn support []
  [:div.m-8
   [:h1.text-2xl "Support"]
   [:p.mt-4
    "If you have any questions or need help, please contact us at "
    [:a.text-blue-500 {:href "mailto:"} "feedback@mail.com"]]])

(defn render [state]
  (let [current-view (or
                      (:current-view state)
                      (-> views first :id))]
    [:div.flex.flex-col.min-h-screen
     (navbar)
     (case current-view
       :route/apps
       [:div.m-8
        [:h1.text-2xl "Apps"]]

       :route/support
       (support))
     (footer)]))
