(ns totap.ui)

(def views
  [{:id :apps
    :text "Apps"
    :href "/"}
   {:id :privacy
    :text "Privacy Policy"
    :href "/privacy"}
   {:id :support
    :text "Support"
    :href "/support"}])

(def feedback-email [:a.text-blue-500 {:href "mailto:"} "feedback@totap.de"])

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
    feedback-email]])

(defn privacy []
  [:div.mx-auto.w-full.max-w-4xl.px-6.py-10
   [:article {:class "prose prose-base w-full max-w-3xl"}
    [:h1.text-2xl "Privacy Policy"]

    [:p "This privacy policy applies to all mobile applications developed and published by Tobias Ostner."]

    [:h2.mt-8 "1. Controller (Verantwortlicher)"]
    [:p "Tobias Ostner"]
    [:p "Germany"]
    [:p "Email: " feedback-email]

    [:h2.mt-8 "2. General Information"]
    [:p "We take the protection of your personal data seriously. Personal data is processed in accordance with the General Data Protection Regulation (GDPR) and applicable national laws."]

    [:h2.mt-8 "3. Data Collection and Processing"]
    [:p "Our apps are designed to work without requiring users to provide personal data."]
    [:p "However, to ensure stability and improve performance, we use a third-party service:"]
    [:ul.list-disc.pl-6
     [:li "Sentry (error monitoring)"]]
    [:p "Sentry may collect the following data:"]
    [:ul.list-disc.pl-6
     [:li "Device type"]
     [:li "Operating system version"]
     [:li "App version"]
     [:li "Crash logs and diagnostic data"]
     [:li "IP address (processed by Sentry servers)"]]
    [:p "This data is used exclusively to identify and fix technical issues."]
    [:p "No tracking, profiling, or marketing use takes place."]

    [:h2.mt-8 "4. Legal Basis for Processing (Art. 6 GDPR)"]
    [:p "The processing of diagnostic data is based on Art. 6(1)(f) GDPR (legitimate interest)."]
    [:p "Our legitimate interest is to ensure the stability, security, and proper functioning of our apps."]

    [:h2.mt-8 "5. Data Sharing"]
    [:p "Data may be processed by:"]
    [:ul.list-disc.pl-6
     [:li "Sentry (Functional Software, Inc., USA)"]]
    [:p "We ensure that appropriate safeguards are in place for international data transfers, such as Standard Contractual Clauses, where required."]
    [:p "No data is sold or shared for advertising purposes."]

    [:h2.mt-8 "6. Data Retention"]
    [:p "Diagnostic data is retained only as long as necessary to identify and resolve errors."]

    [:h2.mt-8 "7. Your Rights (GDPR)"]
    [:p "You have the right to:"]
    [:ul.list-disc.pl-6
     [:li "Access your data (Art. 15 GDPR)"]
     [:li "Rectification (Art. 16 GDPR)"]
     [:li "Erasure (Art. 17 GDPR)"]
     [:li "Restriction of processing (Art. 18 GDPR)"]
     [:li "Data portability (Art. 20 GDPR)"]
     [:li "Object to processing (Art. 21 GDPR)"]]
    [:p "You also have the right to lodge a complaint with a supervisory authority."]

    [:h2.mt-8 "8. Contact"]
    [:p "If you have any questions about data protection, contact:"]
    feedback-email

    [:h2.mt-8 "9. Changes to this Privacy Policy"]
    [:p "We may update this privacy policy from time to time. The latest version is always available at this URL."]]])

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

       :route/privacy
       (privacy)

       :route/support
       (support))
     (footer)]))
