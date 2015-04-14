Rails.application.routes.draw do
  devise_for :users
  resources :users_admin, :controller => 'users'
  resources :activities
  resources :semesters
end
