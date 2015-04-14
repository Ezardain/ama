# Model ActivityReport
# Table activity_reports
class ActivityReport < ActiveRecord::Base
  belongs_to :user
  belongs_to :activity_instance
end
