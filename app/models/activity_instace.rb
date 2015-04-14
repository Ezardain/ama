# Model ActivityInstance
# Table activity_instances
class ActivityInstance < ActiveRecord::Base
  belongs_to :activity
  belongs_to :semester
end
