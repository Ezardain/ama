Rails.application.routes.draw do
  devise_for :users
  resources :users_admin, :controller => 'users'
  resources :activities
  resources :semesters
  resources :activity_sessions
  resources :groups
  resources :activity_reports
  resources :attachments
end
