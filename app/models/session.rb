# Model Session
# Table session
class Session < ActiveRecord::Base
  belongs_to :activity_instance
end