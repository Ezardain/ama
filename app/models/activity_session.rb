# Model ActivitySession
# Table activity_sessions
class ActivitySession < ActiveRecord::Base
  belongs_to :activity_instance
end